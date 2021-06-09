package com.mycom.student_management.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.mycom.student_management.entity.StudentInfo;
import com.mycom.student_management.jparepository.InfoJpaRepository;
import com.mycom.student_management.jparepository.ScoreJpaRepository;



@Controller
public class InfoController {
	
	@Autowired
	InfoJpaRepository infoJpaRepository;
	
	@Autowired
	ScoreJpaRepository scoreJpaRepository;
	
	// 학생 정보 입력
	@GetMapping("/user/add_info")
	public String add_info(
			@ModelAttribute("studentInfo") StudentInfo studentInfo
			) {
		
//		StudentInfo studentInfo = new StudentInfo("99999", "정민주", null, null, "01012341234");
//		infoJpaRepository.save(studentInfo);
		
		return "contents/student_info/add";
	}
	
	// 입력된 정보 저장
	@PostMapping("/user/add_info_result")
	public String add_info_result(
			Model model,
			@Valid @ModelAttribute("studentInfo") StudentInfo studentInfo,
			BindingResult bindingResult,
			@RequestParam("file") MultipartFile photo
//			@RequestParam("student_no") String student_no,
//			@RequestParam("name") String name,
//			@RequestParam("photo") MultipartFile photo,
//			@RequestParam("tel") String tel
			) throws IOException {
		
		if(bindingResult.hasErrors()) {
			return "contents/student_info/add";
		}
		
		String student_no_exist_result = "";
		String impossible = "";
		

		if(infoJpaRepository.existsById(studentInfo.getStudent_no())) {
			impossible = "이미 등록되어 있는 학사번호입니다. 재등록하려면 리스트에서 수정하세요.";
			model.addAttribute("impossible", impossible);
			
			return "contents/student_info/add";
		}
		else {
			if(!photo.isEmpty()) {
				InputStream is = photo.getInputStream();
				byte[] image = is.readAllBytes();
				
				studentInfo.setPhoto(image);
				studentInfo.setContent_type(photo.getContentType());
				}
				
//			StudentInfo studentInfo = new StudentInfo();
//			studentInfo.setStudent_no(student_no);
//			studentInfo.setName(name);
//			studentInfo.setTel(tel);
			
			infoJpaRepository.save(studentInfo);
			model.addAttribute("studentInfo", studentInfo);
			
//			model.addAttribute("studentInfo", studentInfo);
			
			List<StudentInfo> studentinfoList = infoJpaRepository.findAll();
			model.addAttribute("studentinfoList", studentinfoList);
			
			return "contents/student_info/list";
		}
	}
	
	// 학셍 정보 리스트
	@GetMapping("/user/info_list")
	public String info_list(
			Model model
			) {
		
		List<StudentInfo> studentinfoList = infoJpaRepository.findAll();
		model.addAttribute("studentinfoList", studentinfoList);
		return "contents/student_info/list";
	}
	
	
	// image 뿌리기
	@GetMapping("/user/get_image/{student_no}")
	public void get_image(
			@PathVariable("student_no") String student_no,
			HttpServletResponse response
			) throws IOException {
		
		Optional<StudentInfo> studentInfoOpational = infoJpaRepository.findById(student_no);
		
		if(studentInfoOpational.isPresent()) {
		StudentInfo studentInfo = studentInfoOpational.orElse(null);
		
		response.setContentType(studentInfo.getContent_type());
		OutputStream os = response.getOutputStream();
		byte byteArray[] =  studentInfo.getPhoto();
		os.write(byteArray);
		os.flush();
		os.close();
	}
	}
	
	// 학생 정보 수정
	@GetMapping("/user/edit/{student_no}/{name}")
	public String edit(
//			@ModelAttribute("studentInfo") StudentInfo studentInfo
			Model model,
			@PathVariable String student_no	
			) {

		Optional<StudentInfo> studentInfoOptional 
		= infoJpaRepository.findById(student_no);

		if(studentInfoOptional.isPresent()) {
			StudentInfo studentInfo = studentInfoOptional.orElse(null);
			model.addAttribute("studentInfo", studentInfo);
		}
		
		return "contents/student_info/edit";
	}
	
	@PostMapping("/user/edit_info_result")
	public String edit_info_result(
			Model model,
			@Valid @ModelAttribute("studentInfo") StudentInfo studentInfo,
			BindingResult bindingResult,
			@RequestParam("file") MultipartFile photo
			) throws IOException {
		
		if(!photo.isEmpty()) {
			InputStream is = photo.getInputStream();
			byte[] image = is.readAllBytes();
			
			studentInfo.setPhoto(image);
			studentInfo.setContent_type(photo.getContentType());
			}

		infoJpaRepository.save(studentInfo);
		model.addAttribute("studentInfo", studentInfo);
		
		List<StudentInfo> studentinfoList = infoJpaRepository.findAll();
		model.addAttribute("studentinfoList", studentinfoList);
		
		return "contents/student_info/list";
	}
	
	// 학생 정보 삭제
	@GetMapping("/user/delete/{student_no}")
	public String delelte(
			Model model,
			@ModelAttribute("studentInfo") StudentInfo studentInfo
			) {

		infoJpaRepository.deleteById(studentInfo.getStudent_no());
		
		
		List<StudentInfo> studentinfoList = infoJpaRepository.findAll();
		model.addAttribute("studentinfoList", studentinfoList);
		
		return "contents/student_info/list";
	}
	
	// 학생 정보 상세
	@GetMapping("/user/details/{student_no}")
	public String details(
			Model model,
			@PathVariable("student_no") String student_no
			) {
		
		Optional<StudentInfo> studentInfoOptional = infoJpaRepository.findById(student_no);
		
		if(studentInfoOptional.isPresent()) {
			StudentInfo studentInfo = studentInfoOptional.orElse(null);
			model.addAttribute("studentInfo", studentInfo);
		}
		
		// test
//		List<StudentInfo> studentInfoScore = infoJpaRepository.getScoresByScoreDesc();
//		model.addAttribute("studentInfoScore", studentInfoScore);
		
		
		return "contents/student_info/details";
	}
	
	// 학생 정보 검색 by 학사번호
	@PostMapping("/user/find_info_by_student_no")
	public String find_info_by_student_no_process(
			Model model,
			@RequestParam("student_no") String student_no 
			) {
		
		Optional<StudentInfo> studentInfoOptional = infoJpaRepository.findById(student_no);
		
		if(studentInfoOptional.isPresent()) {
			StudentInfo studentInfo = studentInfoOptional.orElse(null);
			model.addAttribute("studentInfo", studentInfo);
		}
		
		return "contents/student_info/find";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
