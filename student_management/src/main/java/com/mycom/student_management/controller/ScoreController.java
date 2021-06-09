package com.mycom.student_management.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.student_management.entity.StudentScore;
import com.mycom.student_management.jparepository.InfoJpaRepository;
import com.mycom.student_management.jparepository.ScoreJpaRepository;


@Controller
public class ScoreController {
	
	@Autowired
	ScoreJpaRepository scoreJpaRepository;
	
	@Autowired
	InfoJpaRepository infoJpaRepository;
	
	// 성적 입력
	@GetMapping("/user/add_score")
	public String add_score(
			@ModelAttribute("studentScore") StudentScore studentScore
			) {
		return "contents/student_score/add";
	}
	
	// 입력된 성적 저장
	@PostMapping("/user/add_score_result")
	public String add_score_result(
			Model model,
			@Valid @ModelAttribute("studentScore") StudentScore studentScore,
			BindingResult bindingResult,
			@RequestParam("button") String buttonType
			) {
		
		if(bindingResult.hasErrors()) {
			return "contents/student_score/add";
		}
		
		
		String student_no_exist_result="";
		String impossible = "";
		
		if(buttonType.equals("확인")) {		// 학사번호 존재 여부 확인버튼

			String result = scoreJpaRepository.hoho(studentScore.getStudent_no());
			if (result ==null) {
				result = "0";
			}
			
			
			
			if (infoJpaRepository.existsById(studentScore.getStudent_no())&&!result.equals("0")) {
				student_no_exist_result="이미 성적을 등록하였습니다. 재등록하려면 리스트에서 수정하세요.";
				model.addAttribute("student_no_exist_result", student_no_exist_result);
				
				return "contents/student_score/add";
			}
			
			else if (infoJpaRepository.existsById(studentScore.getStudent_no())&&result.equals("0")) {
				student_no_exist_result="존재하는 학사번호입니다. 성적을 등록하세요.";
				model.addAttribute("student_no_exist_result", student_no_exist_result);
				
				return "contents/student_score/add";
				
			}
			else {
				student_no_exist_result="존재하지 않는 학사번호입니다.";
				model.addAttribute("student_no_exist_result", student_no_exist_result);
				
				return "contents/student_score/add";
			}
			
		}

		
		else {		// 성적 등록 버튼
			
			String result = scoreJpaRepository.hoho(studentScore.getStudent_no());
			if (result ==null) {
				result = "0";
			}
			
			if(infoJpaRepository.existsById(studentScore.getStudent_no())&&!result.equals("0")) {
				student_no_exist_result="이미 성적을 등록하였습니다. 재등록하려면 리스트에서 수정하세요.";
				model.addAttribute("student_no_exist_result", student_no_exist_result);
				
				return "contents/student_score/add";
			}
			
			else if(infoJpaRepository.existsById(studentScore.getStudent_no())&&result.equals("0")) {
				scoreJpaRepository.save(studentScore);
				model.addAttribute("studentScore", studentScore);
				
				List<StudentScore> totalaverageList = scoreJpaRepository.getScoresByScoreDesc();
				model.addAttribute("totalaverageList", totalaverageList);
				
				return "contents/student_score/list";
			}
			
			else {
				impossible="존재하지 않는 학사번호입니다. 학사번호를 먼저 등록해주세요";
				model.addAttribute("impossible", impossible);
				
				return "contents/student_score/add";
			}
		}
	}
	
	// 성적 리스트
	@GetMapping("/user/score_list")
	public String score_list(
			Model model
			) {
		
		List<StudentScore> totalaverageList = scoreJpaRepository.getScoresByScoreDesc();
		model.addAttribute("totalaverageList", totalaverageList);
		
		return "contents/student_score/list";
	}
	
	// 성적 수정
	@GetMapping("/user/edit_score/{id}/{student_no}")
	public String score_edit(
			@ModelAttribute("studentScore") StudentScore studentScore
			) {
		return "contents/student_score/edit";
	}
	
	@PostMapping("/user/edit_score_result")
	public String score_edit_result(
			Model model,
			@Valid @ModelAttribute("studentScore") StudentScore studentScore,
			BindingResult bindingResult
			) {
		scoreJpaRepository.save(studentScore);
		model.addAttribute("studentScore", studentScore);
		
		List<StudentScore> totalaverageList = scoreJpaRepository.getScoresByScoreDesc();
		model.addAttribute("totalaverageList", totalaverageList);
		
		return "contents/student_score/list";

	}
	
	// 성적 삭제
	@GetMapping("/user/delete_score/{id}")
	public String score_delete(
			Model model,
			@ModelAttribute("studentScore") StudentScore studentScore
			) {
		
		scoreJpaRepository.deleteById(studentScore.getId());
		
		List<StudentScore> totalaverageList = scoreJpaRepository.getScoresByScoreDesc();
		model.addAttribute("totalaverageList", totalaverageList);
		
		return "contents/student_score/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
