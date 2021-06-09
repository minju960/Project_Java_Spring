package com.mycom.student_management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
@Table(name="student_score")
public class StudentScore {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@NotNull
	@Range(min=0, max=100)
	private int guk;
	
	@NotNull
	@Min(0)
	@Max(100)
	private int math;
	
	@NotNull
	@Min(0)
	@Max(100)
	private int sahee;
	
	private int total;
	private double average;
	

	@NotBlank
	private String student_no;
	
	public StudentScore() {
	}

	public StudentScore(int guk, int math, int sahee, int total, double average, String student_no) {
		this.guk = guk;
		this.math = math;
		this.sahee = sahee;
		this.total = total;
		this.average = average;
		this.student_no = student_no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGuk() {
		return guk;
	}

	public void setGuk(int guk) {
		this.guk = guk;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSahee() {
		return sahee;
	}

	public void setSahee(int sahee) {
		this.sahee = sahee;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	@Override
	public String toString() {
		return "StudentScore [id=" + id + ", guk=" + guk + ", math=" + math + ", sahee=" + sahee + ", total=" + total
				+ ", average=" + average + ", student_no=" + student_no + "]";
	}

	

	
	
	
}
