package com.rolvatech.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rolvatech.studentmanagement.dto.BranchRequestDto;
import com.rolvatech.studentmanagement.dto.BranchResponceDto;
import com.rolvatech.studentmanagement.model.BranchModel;
import com.rolvatech.studentmanagement.model.CollegeModel;
import com.rolvatech.studentmanagement.repo.BranchRepo;
import com.rolvatech.studentmanagement.repo.CollegeRepo;

@Service
public class BranchService {
	@Autowired
	BranchRepo BranchRepo;
	@Autowired
	CollegeRepo collegeRepo;
	
	@Autowired
	ModelMapper  modelMapper;
	
	public BranchResponceDto addBranches(BranchRequestDto brt) {
		
		CollegeModel college=collegeRepo.findById(brt.getCollege_id())
				.orElseThrow(() -> new RuntimeException("College not found with this id: " + brt.getCollege_id()));
		
		
		BranchModel branchModel=modelMapper.map(brt, BranchModel.class);
		branchModel.setBranch_id(null);
		  branchModel.setCollegeModel(college);
		BranchModel saved=BranchRepo.save(branchModel);
		BranchResponceDto responce=modelMapper.map(saved, BranchResponceDto.class);
		responce.setCollege_id(college.getCollege_id());
		responce.setCollege_Name(college.getCollege_Name());

		
		return responce;		
	}
	
	public List<BranchResponceDto> getAllBranches(){
		List<BranchModel> branchs=BranchRepo.findAll();
		List<BranchResponceDto> branchResponceDto=new ArrayList<>();
		
		for(BranchModel branch:branchs) {
			
			BranchResponceDto responceDto=modelMapper.map(branch, BranchResponceDto.class);
			branchResponceDto.add(responceDto);
			
			if(branch.getCollegeModel()!=null) {
				 responceDto.setCollege_id(branch.getCollegeModel().getCollege_id());
			        responceDto.setCollege_Name(branch.getCollegeModel().getCollege_Name());
				
			}
		}
		
		return branchResponceDto;
		
	}
	
	public BranchResponceDto getById(Integer id) {
		BranchModel branchModel=BranchRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Branch not found with this id: " + id));
		
		BranchResponceDto resopnse=modelMapper.map(branchModel, BranchResponceDto.class);
		
		return resopnse;
	}
	
	public BranchResponceDto updateById(BranchRequestDto brt,Integer id) {
		
		BranchModel existing=BranchRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Branch not found with this id: " + id));
		existing.setBranch_name(brt.getBranch_name());
		BranchModel saved=BranchRepo.save(existing);
		
		BranchResponceDto response=modelMapper.map(saved, BranchResponceDto.class);
		return response;
		
	}

	public String deleteById(Integer id) {
		BranchModel existing=BranchRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Branch not found with this id: " + id));
		BranchRepo.delete(existing);
		
		return "deleted sucessfully";
		
		
	}
}
