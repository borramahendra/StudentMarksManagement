package com.rolvatech.studentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolvatech.studentmanagement.dto.SubjectRequestDto;
import com.rolvatech.studentmanagement.dto.SubjectResponceDto;
import com.rolvatech.studentmanagement.service.SubjectService;

@RestController
@RequestMapping("/api/v1")
public class SubjectsConteroller {
	@Autowired
	SubjectService subjectService;

	@PostMapping("/addSubjects")
	public SubjectResponceDto addSubjects(@RequestBody SubjectRequestDto srd) {
		return subjectService.addSubjects(srd);
	}

	@GetMapping("/getSubjects")
	public List<SubjectResponceDto> getSubjects() {
		return subjectService.getAllSubjects();

	}

	@GetMapping("/getBySubjectId/{id}")
	public SubjectResponceDto getById(@PathVariable("id") int id) {
		return subjectService.getById(id);

	}

}
