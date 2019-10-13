package vip.itellyou.dao;

import java.util.List;

import vip.itellyou.core.dao.BaseDao;

public interface OptionDao extends BaseDao {
	//根据主题id删除属于该主题的全部选项
	public int deleteOptions(int subjectId) throws Exception;
}
