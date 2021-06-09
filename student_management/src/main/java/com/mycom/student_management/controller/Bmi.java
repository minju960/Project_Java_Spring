package com.mycom.student_management.controller;

public class Bmi {
	
	private double height;
	private double weight;
	private double mybmi;
	private String what;
	
	
	public Bmi() {
	}

	

	public Bmi(double height, double weight) {
		this.height = height;
		this.weight = weight;
	
		
	}



	public double getHeight() {
		return height;
	}


	public double getWeight() {
		return weight;
	}


	public void mybmi() {
		height=height*0.01;
		mybmi = weight/(height*height);
		String s = String.format("%.2f", mybmi);
		this.mybmi = Double.parseDouble(s);
	}
	
	public double getMybmi() {
		return mybmi;
	}
	
	public void result() {
		if(this.mybmi>30) {
			this.what = "중도비만";
		}
		else if(this.mybmi>25) {
			this.what = "경도비만";
		}
		else if(this.mybmi>23) {
			this.what = "과체중";
		}
		else if(this.mybmi>18.5) {
			this.what = "정상";
		}
		else {
			this.what = "저체중";
		}
	}
	
	public String getWhat() {
		return what;
	}
	

	
}
