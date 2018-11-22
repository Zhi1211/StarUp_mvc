package com.web.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Opinion")
public class OpinionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer opinionId;
	String opinionName;
	String opinionMail;
	String opinionField;
	String opinionUpTime;
	
	
	
	
	public OpinionBean() {
		super();
	}
	public OpinionBean(Integer opinionId, String opinionName, String opinionMail,
			String opinionField) {
		super();
		this.opinionId = opinionId;
		this.opinionName = opinionName;
		this.opinionMail = opinionMail;
		this.opinionField = opinionField;
	}
	
	
	public OpinionBean(Integer opinionId) {
		super();
		this.opinionId = opinionId;
	}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
	public Integer getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Integer opinionId) {
		this.opinionId = opinionId;
	}

	public String getOpinionName() {
		return opinionName;
	}

	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}

	public String getOpinionMail() {
		return opinionMail;
	}

	public void setOpinionMail(String opinionMail) {
		this.opinionMail = opinionMail;
	}

	public String getOpinionField() {
		return opinionField;
	}

	public void setOpinionField(String opinionField) {
		this.opinionField = opinionField;
	}	
	
	public String getOpinionUpTime() {
		return opinionUpTime;
	}
	
	public void setOpinionUpTime(String opinionUpTime) {
		this.opinionUpTime = opinionUpTime;
	}
}
