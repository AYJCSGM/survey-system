package vip.itellyou.entity;

import java.util.ArrayList;
import java.util.List;

import vip.itellyou.core.format.DateFormatter;

public class Subject {
	private Integer id;
	private String title;
	private int number;
	private Long startTime;
	private Long endTime;
	
	private User master;
	private List options;
	private int optionCount;
	
	private int userCount;
	
	private String startTimeView;
	private String endTimeView;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		this.startTimeView = DateFormatter.toWholeString(startTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView = DateFormatter.toWholeString(endTime);
	}
	public User getMaster() {
		return master;
	}
	public void setMaster(User master) {
		this.master = master;
	}
	public List getOptions() {
		return options;
	}
	public void setOptions(List options) {
		this.options = options;
		this.optionCount = options.size();
	}
	public int getOptionCount() {
		return optionCount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public Subject() {
		super();
		options=new ArrayList();
		master=new User();
	}
	public String getStartTimeView() {
		return startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
		
}
