package vip.itellyou.dao;

import java.util.List;

import vip.itellyou.core.dao.BaseDao;

public interface OptionDao extends BaseDao {
	//��������idɾ�����ڸ������ȫ��ѡ��
	public int deleteOptions(int subjectId) throws Exception;
}
