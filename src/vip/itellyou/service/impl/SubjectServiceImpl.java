package vip.itellyou.service.impl;

import java.util.Date;
import java.util.List;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.dao.OptionDao;
import vip.itellyou.dao.SubjectDao;
import vip.itellyou.dao.impl.OptionDaoImpl;
import vip.itellyou.dao.impl.SubjectDaoImpl;
import vip.itellyou.entity.Option;
import vip.itellyou.entity.OptionQueryModel;
import vip.itellyou.entity.Subject;
import vip.itellyou.entity.SubjectQueryModel;
import vip.itellyou.entity.User;
import vip.itellyou.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{
	private SubjectDao subjectDao;
	private OptionDao optionDao;
	public SubjectServiceImpl(){
		this.subjectDao=new SubjectDaoImpl();
		this.optionDao = new OptionDaoImpl();
	}
	
	@Override
	public void add(Subject subject,User user) throws Exception {
		//检查项目主题的标题
		if(subject.getTitle()==null || subject.getTitle().trim().length()==0){
			throw new ReTryException("项目主题的标题不能为空");
		}
		
		//检查各个选项的内容
		for(Object data:subject.getOptions()){
			Option option = (Option)data;
			if(option.getContent()==null || option.getContent().trim().length()==0){
				throw new ReTryException("每个项目选项的内容不能为空");
			}
		}
		//项目选项个数不能低于2个
		if(subject.getOptions().size()<2){
			throw new ReTryException("选项数量不能低于2");
		}
		
		//各个选项的内容不一致
		for(int i=0;i<subject.getOptions().size();i++){
			Option first = (Option)subject.getOptions().get(i);
			for(int j=i+1;j<subject.getOptions().size();j++){
				Option next = (Option)subject.getOptions().get(j);
				if(first.getContent().equals(next.getContent())){
					throw new ReTryException("每个选项的内容不能一致");
				}
			}
		}
		//同一个发起人不能发起同样主题的投票项目
		SubjectQueryModel queryModel=new SubjectQueryModel();
		queryModel.setTitle(subject.getTitle());
		queryModel.getMaster().setId(user.getId());
		List list = subjectDao.getByCondition(queryModel);
		if(list!=null && list.size()>0){
			throw new ReTryException("同一个发起人不能发起同样主题的投票项目");
		}
		
		//设置开始时间，结束时间和发起人
		long startTime=new Date().getTime();
		subject.setStartTime(startTime);
		subject.setEndTime(startTime+1*24*60*60*1000);
		subject.setMaster(user);
		//保存数据
		subjectDao.insert(subject);
		int subjectId = subjectDao.getGenerateId();
		for(int i=0;i<subject.getOptions().size();i++){
			Option op = (Option)subject.getOptions().get(i);
			op.setIndex(i+1);
			op.setSubjectId(subjectId);
			optionDao.insert(op);
		}
	}

	@Override
	public List getSubjects() throws Exception {
		List list = subjectDao.getAll();
		if(list!=null && list.size()>0){
			for(Object data:list){
				Subject subject = (Subject)data;
				OptionQueryModel queryModel = new OptionQueryModel();
				queryModel.setSubjectId(subject.getId());
				subject.setOptions(optionDao.getByCondition(queryModel));
				subject.setUserCount(subjectDao.getUserCount(subject.getId()));
			}
		}
		return list;
	}

	@Override
	public Subject getSubject(int id) throws Exception {
		Subject subject = (Subject)subjectDao.getModel(id);
		if(subject!=null){			
			OptionQueryModel queryModel = new OptionQueryModel();
			queryModel.setSubjectId(subject.getId());
			subject.setOptions(optionDao.getByCondition(queryModel));
			subject.setUserCount(subjectDao.getUserCount(subject.getId()));
		}
		return subject;
	}

	@Override
	public void modify(Subject subject, User attribute) throws Exception {
		//已经存在投票记录，不允许进行修改
		if(subjectDao.getUserCount(subject.getId())>0){
			throw new Exception("已经存在投票记录，不可以修改");
		}
		//开始修改
		subjectDao.update(subject);
		optionDao.deleteOptions(subject.getId());
		for(int i=0;i<subject.getOptions().size();i++){
			Option op = (Option)subject.getOptions().get(i);
			op.setIndex(i+1);
			op.setSubjectId(subject.getId());
			optionDao.insert(op);
		}
	}
}
