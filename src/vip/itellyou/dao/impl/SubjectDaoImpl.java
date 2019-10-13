package vip.itellyou.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vip.itellyou.core.dao.BaseDaoImpl;
import vip.itellyou.core.dao.BaseQueryModel;
import vip.itellyou.core.dao.DbHelper;
import vip.itellyou.dao.SubjectDao;
import vip.itellyou.entity.Subject;
import vip.itellyou.entity.SubjectQueryModel;
import vip.itellyou.entity.User;

public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	@Override
	public String getSelectAllSql() {		
		return "select * from t_subject ";
	}

	@Override
	public String getSelectSql(BaseQueryModel queryModel) {
		SubjectQueryModel qm = (SubjectQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_subject where 1=1 ");
		if(qm.getTitle()!=null && qm.getTitle().trim().length()>0){
			sb.append(" and title='"+qm.getTitle()+"' ");
		}
		if(qm.getMaster()!=null || qm.getMaster().getId()!=null){
			sb.append(" and userId="+qm.getMaster().getId());
		}
		return sb.toString();
	}

	@Override
	public String getInsertSql(Object data) {	
		Subject subject = (Subject)data;
		return "insert into t_subject(title,number,startTime,endTime,userId) values('"+subject.getTitle()+"',"+subject.getNumber()+","+subject.getStartTime()+","+subject.getEndTime()+","+subject.getMaster().getId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		Subject subject=(Subject)data;
		return "update t_subject set title='"+subject.getTitle()+"',number="+subject.getNumber()+",startTime="+subject.getStartTime()+",endTime="+subject.getEndTime()+" where id="+subject.getId();
	}

	@Override
	public String getDeleteSql(int id) {		
		return "delete from t_subject where id="+id;
	}

	@Override
	public Object rsToModel(ResultSet rs) throws Exception {
		Subject subject= new Subject();
		subject.setId(rs.getInt("id"));
		subject.setTitle(rs.getString("title"));
		subject.setNumber(rs.getInt("number"));
		subject.setStartTime(rs.getLong("startTime"));
		subject.setEndTime(rs.getLong("endTime"));
		subject.getMaster().setId(rs.getInt("userId"));
		
		return subject;
	}

	@Override
	public int getGenerateId() throws Exception {
		int result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT max(id) as id from t_subject";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("id");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

	@Override
	public int getUserCount(int subjectId) throws Exception {
		int result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT COUNT(DISTINCT userId) as cnt FROM t_record WHERE subjectId="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("cnt");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

}
