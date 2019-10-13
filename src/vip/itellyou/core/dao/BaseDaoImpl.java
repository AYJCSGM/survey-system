package vip.itellyou.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl implements BaseDao {

	@Override
	public List getAll() throws Exception{
		Connection con = DbHelper.getConnection();
		String sql = getSelectAllSql();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List list = new ArrayList();
		while(rs.next()){
			list.add(rsToModel(rs));
		}
		DbHelper.closeAll(null, pst, rs);
		return list;
	}

	@Override
	public List getByCondition(BaseQueryModel queryModel) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getSelectSql(queryModel);
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List list = new ArrayList();
		while(rs.next()){
			list.add(rsToModel(rs));
		}
		DbHelper.closeAll(null, pst, rs);
		return list;
	}

	@Override
	public Object getModel(int id) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getSelectAllSql() + "where id="+id;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Object model=null;
		if(rs.next()){
			model = rsToModel(rs);
		}
		DbHelper.closeAll(null, pst, rs);
		return model;
	}
	
	public int insert(Object data) throws Exception{
		Connection con = DbHelper.getConnection();
		String sql = this.getInsertSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}
    public int update(Object data) throws Exception{
    	Connection con = DbHelper.getConnection();
		String sql = this.getUpdateSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
    }
    public int delete(int id) throws Exception{
    	Connection con = DbHelper.getConnection();
		String sql = this.getDeleteSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
    }

    //产生CRUD的sql语句
	public abstract String getSelectAllSql();
	public abstract String getSelectSql(BaseQueryModel queryModel);
	public abstract String getInsertSql(Object data);
	public abstract String getUpdateSql(Object data);
	public abstract String getDeleteSql(int id);
	//将结果集中的记录转换到实体类对象
	public abstract Object rsToModel(ResultSet rs) throws Exception;
}
