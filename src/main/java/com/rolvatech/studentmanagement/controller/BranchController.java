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

import com.rolvatech.studentmanagement.dto.BranchRequestDto;
import com.rolvatech.studentmanagement.dto.BranchResponceDto;
import com.rolvatech.studentmanagement.service.BranchService;

@RestController
@RequestMapping("/api/v1")
public class BranchController {
	@Autowired
	BranchService BranchService;
	@PostMapping("/addBranches")
	public BranchResponceDto addBranches(@RequestBody BranchRequestDto brt) {
		return BranchService.addBranches(brt);
	}
	@GetMapping("/getAllBranches")
	public List<BranchResponceDto> getBranches(){
		return BranchService.getAllBranches();
		
	}
	@GetMapping("/getByBranchID/{id}")
	public BranchResponceDto getById(@PathVariable("id") Integer id) {
		return BranchService.getById(id);
	}
	
	@PutMapping("/updateBranchById/{id}")
	public BranchResponceDto updateById(@RequestBody BranchRequestDto brt,@PathVariable("id") Integer id) {
		return BranchService.updateById(brt, id);
		
	}
	@DeleteMapping("/deleteBranchById/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		return BranchService.deleteById(id);
		
	}

}
