package com.ithwua.bean;

public class New {
	private long id;
	private String title;
	private String content;
	private String createTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "New [id=" + id + ", title=" + title + ", content=" + content + ", createTime=" + createTime + "]";
	}
	

}
