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

import com.rolvatech.studentmanagement.dto.MarksRequestdto;
import com.rolvatech.studentmanagement.dto.MarksResponseDto;
import com.rolvatech.studentmanagement.service.MarksService;

@RestController
@RequestMapping("/api/v1")
public class MarksController {
	@Autowired
	MarksService marksService;

	@PostMapping("/addMarks")
	public MarksResponseDto addMarks(@RequestBody MarksRequestdto mrd) {

		return marksService.addMarks(mrd);

	}

	@GetMapping("/getMarks")
	public List<MarksResponseDto> getAllMarks() {
		return marksService.getAllMarks();

	}

	@GetMapping("/getByMarksId/{id}")
	public MarksResponseDto getById(@PathVariable("id") Integer id) {
		return marksService.getById(id);

	}
	
	@PutMapping("/updateMarksById/{id}")
	public MarksResponseDto updateById(@RequestBody MarksRequestdto mrd,@PathVariable("id") Integer id) {
		return marksService.updateById(mrd, id);
		
	}
	
	@DeleteMapping("deleteMarksById/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		return marksService.deleteById(id);
	}

}
