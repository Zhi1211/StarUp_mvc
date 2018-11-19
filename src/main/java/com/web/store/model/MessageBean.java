package com.web.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class MessageBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer seqno;
	private Integer fromId;
	private String fromAccount;
	private String fromNickname;
	private Integer toId;
	private String toAccount;
	private String toNickname;
	private String posttime;
	private String messageTitle;
	private String messageContent;
	private Integer checkedTag;
	
	public MessageBean() {
		super();
	}

	public MessageBean(Integer seqno, Integer fromId, String fromAccount, String fromNickname, Integer toId,
			String toAccount, String toNickname, String posttime, String messageTitle, String messageContent,
			Integer checkedTag) {
		super();
		this.seqno = seqno;
		this.fromId = fromId;
		this.fromAccount = fromAccount;
		this.fromNickname = fromNickname;
		this.toId = toId;
		this.toAccount = toAccount;
		this.toNickname = toNickname;
		this.posttime = posttime;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.checkedTag = checkedTag;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getFromNickname() {
		return fromNickname;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getCheckedTag() {
		return checkedTag;
	}

	public void setCheckedTag(Integer checkedTag) {
		this.checkedTag = checkedTag;
	}
	
}
