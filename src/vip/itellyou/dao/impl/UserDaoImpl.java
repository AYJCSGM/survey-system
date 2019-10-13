package vip.itellyou.dao.impl;

import java.sql.ResultSet;

import vip.itellyou.core.dao.BaseDaoImpl;
import vip.itellyou.core.dao.BaseQueryModel;
import vip.itellyou.dao.UserDao;
import vip.itellyou.entity.User;
import vip.itellyou.entity.UserQueryModel;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public String getSelectAllSql() {		
		return "select * from t_user ";
	}

	@Override
	public String getSelectSql(BaseQueryModel queryModel) {
		UserQueryModel qm = (UserQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_user where 1=1 ");
		if(qm.getName()!=null && qm.getName().trim().length()>0){
			sb.append(" and name='"+qm.getName()+"' ");
		}
		if(qm.getPwd()!=null && qm.getPwd().trim().length()>0){
			sb.append(" and pwd='"+qm.getPwd()+"' ");
		}
		return sb.toString();
	}

	@Override
	public String getInsertSql(Object data) {	
		User user = (User)data;
		return "insert into t_user(name,pwd,online) values('"+user.getName()+"','"+user.getPwd()+"',1)";
	}

	@Override
	public String getUpdateSql(Object data) {
		User user = (User)data;
		return "update t_user set name='"+user.getName()+"',pwd='"+user.getPwd()+"',online="+user.getOnline()+" where id="+user.getId();
	}

	@Override
	public String getDeleteSql(int id) {		
		return "delete from t_user where id="+id;
	}

	@Override
	public Object rsToModel(ResultSet rs) throws Exception {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPwd(rs.getString("pwd"));
		user.setOnline(rs.getInt("online"));
		
		return user;
	}

}
