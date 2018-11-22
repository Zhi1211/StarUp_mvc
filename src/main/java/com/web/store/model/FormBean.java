package com.web.store.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "form")
public class FormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer form_id;
	private Integer user_id;
	private String question_1;
	private String question_2;
	private String question_3;
	private String question_4;
	private String realName;
	private String formProdName;
	private Integer formPrice;
	private Blob formImg;
	private String formImgName;
	private String formIntro;
	private String submitDate;
	private String formMail;
	private MultipartFile formImage;

	@Transient
	@XmlTransient
	public MultipartFile getFormImage() {
		return formImage;
	}
	
	public void setFormImage(MultipartFile formImage) {
		this.formImage = formImage;
	}

	public FormBean(String formMail) {
		super();
		this.formMail = formMail;
	}

	public FormBean(Integer form_id, String question_1, String question_2, String question_3, String question_4,
			String realName, String formProdName, Integer formPrice, Blob formImg, String formImgName, String formIntro,
			String submitDate,MultipartFile formImage) {
		super();
		this.form_id = form_id;
		this.question_1 = question_1;
		this.question_2 = question_2;
		this.question_3 = question_3;
		this.question_4 = question_4;
		this.realName = realName;
		this.formProdName = formProdName;
		this.formPrice = formPrice;
		this.formImg = formImg;
		this.formImgName = formImgName;
		this.formIntro = formIntro;
		this.submitDate = submitDate;
		this.formImage = formImage;
	}
	public FormBean(){
		
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getForm_id() {
		return form_id;
	}

	public void setForm_id(Integer form_id) {
		this.form_id = form_id;
	}

	public String getQuestion_1() {
		return question_1;
	}

	public void setQuestion_1(String question_1) {
		this.question_1 = question_1;
	}

	public String getQuestion_2() {
		return question_2;
	}

	public void setQuestion_2(String question_2) {
		this.question_2 = question_2;
	}

	public String getQuestion_3() {
		return question_3;
	}

	public void setQuestion_3(String question_3) {
		this.question_3 = question_3;
	}

	public String getQuestion_4() {
		return question_4;
	}

	public void setQuestion_4(String question_4) {
		this.question_4 = question_4;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getFormProdName() {
		return formProdName;
	}

	public void setFormProdName(String formProdName) {
		this.formProdName = formProdName;
	}

	public Integer getFormPrice() {
		return formPrice;
	}

	public void setFormPrice(Integer formPrice) {
		this.formPrice = formPrice;
	}

	public Blob getFormImg() {
		return formImg;
	}

	public void setFormImg(Blob formImg) {
		this.formImg = formImg;
	}

	public String getFormImgName() {
		return formImgName;
	}

	public void setFormImgName(String formImgName) {
		this.formImgName = formImgName;
	}

	public String getFormIntro() {
		return formIntro;
	}

	public void setFormIntro(String formIntro) {
		this.formIntro = formIntro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getFormMail() {
		return formMail;
	}

	public void setFormMail(String formMail) {
		this.formMail = formMail;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	

	
}
