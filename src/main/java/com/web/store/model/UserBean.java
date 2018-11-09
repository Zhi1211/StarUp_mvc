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



@Entity
@Table(name="user")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
		Integer user_id;
		String account;
		String password;
		String name;
		String nickname;
		String gender;
		String birthday; // 1029改String
		String phone;
		String address;
		String photoName;
		Blob photo;
		Clob introduction;
		String regTime; // 1029改String
		long unpaid_amount;
		String password2;
		MultipartFile userImage;
		String userIntro;
//		Set<WorksBean> works = new LinkedHashSet<>();
		
		public UserBean(Integer user_id, String account, String password, String name, String nickname, String gender, String birthday,
				String phone, String address, Blob photo, String photoName, Clob introduction, String regTime, long unpaid_amount) 
		{
			super();
			this.user_id = user_id;
			this.account = account;
			this.password = password;
			this.name = name;
			this.nickname = nickname;
			this.gender = gender;
			this.birthday = birthday;
			this.phone = phone;
			this.address = address;
			this.photo = photo;
			this.photoName = photoName;
			this.introduction = introduction;
			this.regTime = regTime;
			this.unpaid_amount = unpaid_amount;
		}
		
		// without photoName
		public UserBean(Integer user_id, String account, String password, String name, String nickname, String gender, String birthday,
				String phone, String address, Blob photo, Clob introduction, String regTime, long unpaid_amount) {
			super();
			this.user_id = user_id;
			this.account = account;
			this.password = password;
			this.name = name;
			this.nickname = nickname;
			this.gender = gender;
			this.birthday = birthday;
			this.phone = phone;
			this.address = address;
			this.photo = photo;
			this.introduction = introduction;
			this.regTime = regTime;
			this.unpaid_amount = unpaid_amount;
		}

		// empty Constructor
		public UserBean() {
		}
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		public Integer getUser_id() {
			return user_id;
		}
		
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getBirthday() { // 1029改String
			return birthday;
		}
		public void setBirthday(String birthday) { // 1029改String
			this.birthday = birthday;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public String getAddress() {
			return address;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		
		public String getPhotoName() {
			return photoName;
		}
		
		public void setPhotoName(String photoName) {
			this.photoName = photoName;
		}
		
		public Blob getPhoto() {
			return photo;
		}
		
		public void setPhoto(Blob photo) {
			this.photo = photo;
		}
		
		public Clob getIntroduction() {
			return introduction;
		}
	
		public void setIntroduction(Clob introduction) {
			this.introduction = introduction;
		}
	
		public String getRegTime() { // 1029改String
			return regTime;
		}

		public void setRegTime(String regTime) { // 1029改String
			this.regTime = regTime;
		}
		
		public long getUnpaid_amount() {
			return unpaid_amount;
		}
		
		public void setUnpaid_amount(Long unpaid_amount) {
			this.unpaid_amount = unpaid_amount;
		}
		@Transient
		public String getPassword2() {
			return password2;
		}

		public void setPassword2(String password2) {
			this.password2 = password2;
		}
		@Transient
		@XmlTransient
		public MultipartFile getUserImage() {
			return userImage;
		}
		public void setUserImage(MultipartFile userImage) {
			this.userImage = userImage;
		}
		@Transient
		public String getUserIntro() {
			return userIntro;
		}

		public void setUserIntro(String userIntro) {
			this.userIntro = userIntro;
		}
		
}
