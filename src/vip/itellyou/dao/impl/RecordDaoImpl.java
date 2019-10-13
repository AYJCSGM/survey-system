package vip.itellyou.dao.impl;

import java.sql.ResultSet;

import vip.itellyou.core.dao.BaseDaoImpl;
import vip.itellyou.core.dao.BaseQueryModel;
import vip.itellyou.dao.RecordDao;
import vip.itellyou.entity.Record;
import vip.itellyou.entity.RecordQueryModel;

public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {

	@Override
	public String getSelectAllSql() {
		return "select * from t_record";
	}

	@Override
	public String getSelectSql(BaseQueryModel queryModel) {
		RecordQueryModel qm = (RecordQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_record where 1=1 ");
		if(qm.getSubject().getId()!=null){
			sb.append(" subjectId="+qm.getSubject().getId());
		}
		
		return sb.toString();
	}

	@Override
	public String getInsertSql(Object data) {
		Record record=(Record)data;
		
		return "insert into t_record(userId,subjectId,optionId) values("+record.getUser().getId()+","+record.getSubject().getId()+","+record.getOption().getId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		return null;
	}

	@Override
	public String getDeleteSql(int id) {		
		return null;
	}

	@Override
	public Object rsToModel(ResultSet rs) throws Exception {
		Record record= new Record();
		record.setId(rs.getInt("id"));
		record.getUser().setId(rs.getInt("userId"));
		record.getSubject().setId(rs.getInt("subjectId"));
		record.getOption().setId(rs.getInt("optionId"));
		return record;
	}

}
