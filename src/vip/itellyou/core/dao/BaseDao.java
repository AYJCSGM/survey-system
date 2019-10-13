package vip.itellyou.core.dao;

import java.util.List;

public interface BaseDao {
	public List getAll() throws Exception;
	public List getByCondition(BaseQueryModel queryModel) throws Exception;
	public Object getModel(int id) throws Exception; 
    public int insert(Object data) throws Exception;
    public int update(Object data) throws Exception;
    public int delete(int id) throws Exception;
}
