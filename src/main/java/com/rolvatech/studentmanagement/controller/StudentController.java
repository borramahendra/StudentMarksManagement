package com.rolvatech.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolvatech.studentmanagement.dto.LoginRequestDto;
import com.rolvatech.studentmanagement.dto.StudentRequestDto;
import com.rolvatech.studentmanagement.dto.StudentResponseDto;
import com.rolvatech.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@PostMapping("/addStudents")
	public StudentResponseDto addStudents(@RequestBody StudentRequestDto srd) {
		
		return studentService.addStudents(srd);
		
	}
	
	@GetMapping("/getStudents")
	public List<StudentResponseDto> getstudents(){
		return studentService.getAllStudents();
		
	}
	@GetMapping("/getByStudentsId/{id}")
	public StudentResponseDto getById(@PathVariable("id") Integer id) {
		return studentService.getById(id);
		
	}
	@PutMapping("/updateStudentId/{id}")
	public StudentResponseDto updateById(@RequestBody StudentRequestDto srd,@PathVariable("id") Integer id) {
		return studentService.updateById(srd, id);
		
	}
	@DeleteMapping("/deleteStudentId/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		
		return studentService.deleteById(id);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDto request) {
		return studentService.login(request);
		
	}

}
