package com.mycom.student_management.controller;

public class Calc {
	
	// 필드 = 데이터
	private double v1;
	private double v2;
	private double result;
	
	// 기본 생성자 : 인수 없다
	public Calc() {
	 System.out.println("기본 생성자 호출됨");
	}
	
	//getter setter
	public double getV1() {
		return v1;
	}


	public void setV1(double v1) {
		this.v1 = v1;
	}


	public double getV2() {
		return v2;
	}


	public void setV2(double v2) {
		this.v2 = v2;
	}


	public double getResult() {
		return result;
	}


	public void setResult(double result) {
		this.result = result;
	}


	// 생성자 = 메소드 -> 중복 안생기게 할 수 ㅇ / 리턴형 없음
	// 생성자는 오브젝트 생성할 때 맨 먼저 호출된다
	public Calc(double v1, double v2) {
		this.v1 = v1;					// this 붙이는 이유 = ?
		this.v2 = v2;
		System.out.println("다중 생성자 호출됨");
	}
	
	// 메소드 = 기능
	public void plus() {
		this.result = this.v1 + this.v2;		// 위에 형(double or int)을 안주면 void / 형을 주면 return 줘야함 
	}

	public void minus() {
		this.result = this.v1 - this.v2;		
	}
	
	public void multiply() {
		this.result = this.v1 * this.v2;		
	}
	
	public void divide() {
		this.result = this.v1 / this.v2;		
	}

	
	
	
}
