package com.mycom.student_management.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LottoController {
	
	
	@GetMapping("/user/lotto_input")
	public String lotto_input() {
		return "contents/lotto_input";
	}
	
	@PostMapping("/user/lotto_result")
	public String lotto_result(
			Model model
			) {
		
		
		Random random = new Random();
		ArrayList<String> lottoNumberList = new ArrayList<String>();
		String number_str="";
		for(int i=1; i<=45; i++) {
			int number = new Random().nextInt(45)+1;
			
			number_str = Integer.toString(number);
			
			lottoNumberList.add(number_str);
		}
		
		model.addAttribute("lottoNumberList", lottoNumberList);
		
		return "contents/lotto_result";
	}
	
	
	@GetMapping("/user/lotto_input_2")
	public String lotto_input_2() {
		return "contents/lotto_input_2";
	}
	
	
	@PostMapping("/user/lotto_result_2")
	public String lotto_result_2(
			Model model,	//데이터를 뷰페이지에 전달하는 역할
			@RequestParam("number_1") int number_1,
			@RequestParam("number_2") int number_2,
			@RequestParam("number_3") int number_3,
			@RequestParam("number_4") int number_4,
			@RequestParam("number_5") int number_5,
			@RequestParam("number_6") int number_6
			
			) {
		
		//ArrayList<Integer> numberList = new ArrayList<Integer>();
		//List : ArrayList의 부모 / 이게 FM
		List<Integer> numberList = new ArrayList<Integer>();
		for(int i=1; i<=45; i++) {
			numberList.add(i);
		}
		//System.out.println(numberList);
		//섞는다
		Collections.shuffle(numberList);
		//System.out.println(numberList);
		
		List<Integer> lottoList = new ArrayList<Integer>();
		for(int i=0; i<7; i++) {
			int number = numberList.get(i);	
			//System.out.println(number);
			lottoList.add(number);
		}
		
		List<Integer> mynumberList = new ArrayList<Integer>();
		mynumberList.add(number_1);
		mynumberList.add(number_2);
		mynumberList.add(number_3);
		mynumberList.add(number_4);
		mynumberList.add(number_5);
		mynumberList.add(number_6);
		
		List<Integer> correctList = new ArrayList<Integer>();
		int cnt = 0;
		for (int i=0;i<6;i++) {
			for(int j=0; j<6; j++) {
				if(lottoList.get(i)==mynumberList.get(j)) {
					cnt ++;
					correctList.add(lottoList.get(i));
				}
				if(cnt==5) {
					if(lottoList.get(6)==mynumberList.get(j)) {
						String a = "2등, 축하합니다!"+ "<br/>" + "총 60,000,000원 당첨";
					}
				}
			}
		}
		System.out.println(correctList);
		
		String a ="";
		if (cnt <= 2) {
			a = "아쉽게도, 낙첨되었습니다.";
		}
		
		else if (cnt == 3) {
			a = "5등, 축하합니다!" + "<br/>" + "총 5,000원 당첨";
		}
		
		else if (cnt == 4) {
			a = "4등, 축하합니다!" + "<br/>" + "총 50,000원 당첨";
		}
		
		else if (cnt == 5) {
			a = "3등, 축하합니다!" + "<br/>" + "총 2,000,000원 당첨";
		}
		
		else if (cnt == 6) {
			a = "1등, 축하합니다!" + "<br/>" + "총 3,000,000,000원 당첨";
		}
		
		String s = String.format("%d개 맞췄습니다!" + "<br/>" + "%s",cnt,a);

		model.addAttribute("lottoList", lottoList);
		model.addAttribute("number_1", number_1);
		model.addAttribute("number_2", number_2);
		model.addAttribute("number_3", number_3);
		model.addAttribute("number_4", number_4);
		model.addAttribute("number_5", number_5);
		model.addAttribute("number_6", number_6);
		model.addAttribute("s", s);
		
		return "contents/lotto_result_2";
	}
	
}
