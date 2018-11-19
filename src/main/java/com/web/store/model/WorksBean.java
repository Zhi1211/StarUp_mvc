package com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="works")
public class WorksBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer user_Id;
	private String author;
	private Integer works_id;
	private String worksName;
	private String worksIntro;
	private String worksImgName;
	@JsonIgnore
	private Blob worksImg;
	private String worksUpDate;
	
	//進階
	private String detail_1;//作品細節_1
	private String captionImgName_1;//照片_1
	@JsonIgnore
	private Blob captionImg_1;
	
	private String detail_2;//作品細節_2
	private String captionImgName_2;//照片_2
	@JsonIgnore
	private Blob captionImg_2; 
	
	private MultipartFile  worksPhoto;
	private MultipartFile  captionPhoto_1;
	private MultipartFile  captionPhoto_2;
	
	private Clob comment;
	
	public WorksBean(Integer works_id, String worksName, String worksIntro, String worksImgName, Blob worksImg,
		  String worksUpDate,String detail_1, String captionImgName_1, Blob captionImg_1, String detail_2, 
		   String captionImgName_2, Blob captionImg_2,Integer user_Id, String author, Clob comment) {
		super();
		this.works_id = works_id;
		this.worksName = worksName;
		this.worksIntro = worksIntro;
		this.worksImgName = worksImgName;
		this.worksImg = worksImg;
		this.worksUpDate = worksUpDate;
		this.detail_1 = detail_1;
		this.captionImgName_1 = captionImgName_1;
		this.captionImg_1 = captionImg_1;
		this.detail_2 = detail_2;
		this.captionImgName_2 = captionImgName_2;
		this.captionImg_2 = captionImg_2;
		this.user_Id=user_Id;
		this.author = author;
		this.comment = comment;
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

	public String getWorksUpDate() {
		return worksUpDate;
	}

	public void setWorksUpDate(String worksUpDate) {
		this.worksUpDate = worksUpDate;
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

	
	public Integer getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}

	public String getAuthor() {
		return  author;
	}

	public void setAuthor(String  author) {
		this. author =  author;
	}

	
	@Transient
	@XmlTransient
	public MultipartFile getWorksPhoto() {
		return worksPhoto;
	}

	public void setWorksPhoto(MultipartFile worksPhoto) {
		this.worksPhoto = worksPhoto;
	}
	
	@Transient
	@XmlTransient
	public MultipartFile getCaptionPhoto_1() {
		return captionPhoto_1;
	}

	public void setCaptionPhoto_1(MultipartFile captionPhoto_1) {
		this.captionPhoto_1 = captionPhoto_1;
	}
	
	@Transient
	@XmlTransient
	public MultipartFile getCaptionPhoto_2() {
		return captionPhoto_2;
	}

	public void setCaptionPhoto_2(MultipartFile captionPhoto_2) {
		this.captionPhoto_2 = captionPhoto_2;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}
	
	
}