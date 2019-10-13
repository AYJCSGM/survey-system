package vip.itellyou.service.impl;

import java.util.Date;
import java.util.List;

import vip.itellyou.core.exception.ReTryException;
import vip.itellyou.dao.RecordDao;
import vip.itellyou.dao.SubjectDao;
import vip.itellyou.dao.impl.RecordDaoImpl;
import vip.itellyou.dao.impl.SubjectDaoImpl;
import vip.itellyou.entity.Record;
import vip.itellyou.entity.Subject;
import vip.itellyou.service.RecordService;

public class RecordServiceImpl implements RecordService {
    private SubjectDao subjectDao;
    private RecordDao recordDao;
    public RecordServiceImpl(){
    	this.subjectDao=new SubjectDaoImpl();
    	this.recordDao=new RecordDaoImpl();
    }
    
	@Override
	public void vote(List<Record> records) throws Exception {
		Subject subject= (Subject)subjectDao.getModel(records.get(0).getSubject().getId());
		if(subject.getNumber()==1 && records.size()!=1 ||
		   subject.getNumber()==2 && records.size()<2
		){
			throw new ReTryException("û�а��յ�ѡ�Ͷ�ѡ������ѡ��");
		}
		
		//��֤ʱ�䣺
		long curr = new Date().getTime();
		if(curr<subject.getStartTime() || curr>subject.getEndTime()){
			throw new ReTryException("û������Ŀ�涨��ʱ����ͶƱ");
		}
		
		//��ʼͶƱ
		for(Record record:records){
			recordDao.insert(record);
		}
	}

}
