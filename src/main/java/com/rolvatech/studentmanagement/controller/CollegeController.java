package com.rolvatech.studentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolvatech.studentmanagement.dto.CollegeRequestDto;
import com.rolvatech.studentmanagement.dto.CollegeResponceDto;
import com.rolvatech.studentmanagement.service.CollegeService;

@RestController
@RequestMapping("/api/v1")
public class CollegeController {
	@Autowired
	CollegeService CollegeService;
	
	@PostMapping("/addCollege")
	public CollegeResponceDto addColleges(@RequestBody CollegeRequestDto crd) {
		 return CollegeService.addCollege(crd);
			
	}
	
	@GetMapping("/getAllColleges")
	public List<CollegeResponceDto> getColleges(){
		return CollegeService.getAllColleges();
	}
	
	@GetMapping("/GetByCollegeId/{id}")
	public CollegeResponceDto getCollegeById(@PathVariable("id") int id) {
		return CollegeService.getById(id);
	}

}
