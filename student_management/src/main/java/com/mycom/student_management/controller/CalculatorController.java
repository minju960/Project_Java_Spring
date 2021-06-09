package com.mycom.student_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.student_management.class_calc.Calc;

@Controller
public class CalculatorController {
	
	@GetMapping("/user/calculator_input2")
	public String calculator_input2() {
		return "contents/calculator_input2";
	}
	
	@PostMapping("/user/calculator_result2")
	public String calculator_result2(
			Model model,
			@RequestParam("v1") double v1,
			@RequestParam("v2") double v2,
			@RequestParam("button") String button
			) {
		
		
		
		// 오브젝트 생성 / 오브젝트 = 실체
		// 객체 지향 프로그래밍 방법
		Calc calc = new Calc(v1, v2);
		
		if(button.equals("＋")) {
			calc.plus();
			//result = v1+v2;
		}
		else if(button.equals("-")) {
			calc.minus();
			//result = v1-v2;
		}
		else if(button.equals("×")) {
			calc.multiply();
			//result = v1*v2;
		}
		else if(button.equals("÷")) {
			calc.divide();
			//result = v1/v2;
		}
		
		
		model.addAttribute("calc", calc);
		model.addAttribute("button", button);
		
		
		return "contents/calculator_result2";
	}
	
}
