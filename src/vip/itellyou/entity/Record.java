package vip.itellyou.entity;

public class Record {
	private Integer id;
	private User user;
	private Subject subject;
	private Option option;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Option getOption() {
		return option;
	}
	public void setOption(Option option) {
		this.option = option;
	}
	public Record() {
		super();
		this.user=new User();
		this.subject=new Subject();
		this.option = new Option();
	}
	
	
}
