package com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="works")
public class WorksBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Integer works_id;
	String worksName;
	String worksIntro;
	String worksImgName;
	Blob worksImg;
	String worksUpDate; // 1029改String
	
	//進階
	String caption_1;//標題_1
	String detail_1;//內文_1
	String captionImgName_1;//照片_1
	Blob captionImg_1;
	
	String caption_2;//標題_1
	String detail_2;//內文_1
	String captionImgName_2;//照片_1
	Blob captionImg_2;
	public WorksBean(Integer works_id, String worksName, String worksIntro, String worksImgName, Blob worksImg,
			String worksUpDate, String caption_1, String detail_1, String captionImgName_1, Blob captionImg_1,
			String caption_2, String detail_2, String captionImgName_2, Blob captionImg_2) {
		super();
		this.works_id = works_id;
		this.worksName = worksName;
		this.worksIntro = worksIntro;
		this.worksImgName = worksImgName;
		this.worksImg = worksImg;
		this.worksUpDate = worksUpDate;
		this.caption_1 = caption_1;
		this.detail_1 = detail_1;
		this.captionImgName_1 = captionImgName_1;
		this.captionImg_1 = captionImg_1;
		this.caption_2 = caption_2;
		this.detail_2 = detail_2;
		this.captionImgName_2 = captionImgName_2;
		this.captionImg_2 = captionImg_2;
	}
	
	public WorksBean() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getWorks_id() {
		return works_id;
	}

	public void setWorks_id(Integer works_id) {
		this.works_id = works_id;
	}

	public String getWorksName() {
		return worksName;
	}

	public void setWorksName(String worksName) {
		this.worksName = worksName;
	}

	public String getWorksIntro() {
		return worksIntro;
	}

	public void setWorksIntro(String worksIntro) {
		this.worksIntro = worksIntro;
	}

	public String getWorksImgName() {
		return worksImgName;
	}

	public void setWorksImgName(String worksImgName) {
		this.worksImgName = worksImgName;
	}

	public Blob getWorksImg() {
		return worksImg;
	}

	public void setWorksImg(Blob worksImg) {
		this.worksImg = worksImg;
	}

	public String getWorksUpDate() { // 1029改String
		return worksUpDate;
	}

	public void setWorksUpDate(String worksUpDate) { // 1029改String
		this.worksUpDate = worksUpDate;
	}

	public String getCaption_1() {
		return caption_1;
	}

	public void setCaption_1(String caption_1) {
		this.caption_1 = caption_1;
	}

	public String getDetail_1() {
		return detail_1;
	}

	public void setDetail_1(String detail_1) {
		this.detail_1 = detail_1;
	}

	public String getCaptionImgName_1() {
		return captionImgName_1;
	}

	public void setCaptionImgName_1(String captionImgName_1) {
		this.captionImgName_1 = captionImgName_1;
	}

	public Blob getCaptionImg_1() {
		return captionImg_1;
	}

	public void setCaptionImg_1(Blob captionImg_1) {
		this.captionImg_1 = captionImg_1;
	}

	public String getCaption_2() {
		return caption_2;
	}

	public void setCaption_2(String caption_2) {
		this.caption_2 = caption_2;
	}

	public String getDetail_2() {
		return detail_2;
	}

	public void setDetail_2(String detail_2) {
		this.detail_2 = detail_2;
	}

	public String getCaptionImgName_2() {
		return captionImgName_2;
	}

	public void setCaptionImgName_2(String captionImgName_2) {
		this.captionImgName_2 = captionImgName_2;
	}

	public Blob getCaptionImg_2() {
		return captionImg_2;
	}

	public void setCaptionImg_2(Blob captionImg_2) {
		this.captionImg_2 = captionImg_2;
	}

	
	
	
}