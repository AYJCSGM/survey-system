package vip.itellyou.service;

import java.util.List;

import vip.itellyou.entity.Record;

public interface RecordService {
	public void vote(List<Record> records) throws Exception;
}
