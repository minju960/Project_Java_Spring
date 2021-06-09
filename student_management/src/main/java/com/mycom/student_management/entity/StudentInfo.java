package com.mycom.student_management.entity;



import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="student_info")
public class StudentInfo {
	
	@Id
	@NotBlank
	@Pattern(regexp = "[0-9]{5}", message="5자리 학사번호 입력")
	private String student_no;
	
	@NotBlank
	private String name;
	
	@Lob
	private byte[] photo;
	
	private String content_type;
	
	private String tel;
	
	

	public StudentInfo() {
	}



	public StudentInfo(@NotBlank String student_no, String name, byte[] photo, String content_type, String tel) {
		super();
		this.student_no = student_no;
		this.name = name;
		this.photo = photo;
		this.content_type = content_type;
		this.tel = tel;
	}



	public String getStudent_no() {
		return student_no;
	}



	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public String getContent_type() {
		return content_type;
	}



	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	@Override
	public String toString() {
		return "StudentInfo [student_no=" + student_no + ", name=" + name + ", photo=" + Arrays.toString(photo)
				+ ", content_type=" + content_type + ", tel=" + tel + "]";
	}



	

	
	
}
