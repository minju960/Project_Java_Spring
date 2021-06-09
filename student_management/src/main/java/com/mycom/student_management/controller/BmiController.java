package com.mycom.student_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.student_management.class_bmi.Bmi;

@Controller
public class BmiController {
	
	@GetMapping("bmi_input")
	public String bmi_input() {
		return "contents/bmi_input";
	}
	
	@PostMapping("bmi_result")
	public String bmi_result(
			Model model,
			@RequestParam("height") double height,
			@RequestParam("weight") double weight
			) {
		
		
		Bmi bmi = new Bmi(height, weight);
		bmi.mybmi();
		bmi.result();
		
		
		model.addAttribute("bmi", bmi);
		return "contents/bmi_result";
	}
}
