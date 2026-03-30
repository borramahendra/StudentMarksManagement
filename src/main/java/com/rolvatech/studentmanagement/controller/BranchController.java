package com.rolvatech.studentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public BranchResponceDto getById(@PathVariable("id") int id) {
		return BranchService.getById(id);
	}
	
	public BranchResponceDto updateById(BranchRequestDto brt,Integer id) {
		return BranchService.updateById(brt, id);
		
	}

}
