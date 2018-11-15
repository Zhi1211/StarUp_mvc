package com.web.store.model;

public class WorkCommentBean {
	
	private Integer userId;
	private String userNickName;
	private String commentDate;
	private String comment;
	
	public WorkCommentBean() {
		super();
	}

	public WorkCommentBean(Integer userId, String userNickName, String commentDate, String comment) {
		super();
		this.userId = userId;
		this.userNickName = userNickName;
		this.commentDate = commentDate;
		this.comment = comment;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
