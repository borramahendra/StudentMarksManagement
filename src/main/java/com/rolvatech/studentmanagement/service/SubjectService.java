package com.rolvatech.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.dto.SubjectRequestDto;
import com.rolvatech.studentmanagement.dto.SubjectResponceDto;
import com.rolvatech.studentmanagement.model.BranchModel;
import com.rolvatech.studentmanagement.model.SubjectsModel;
import com.rolvatech.studentmanagement.repo.BranchRepo;
import com.rolvatech.studentmanagement.repo.SubjectRepo;

@Service
public class SubjectService {
	@Autowired
	SubjectRepo subjectRepo;
	@Autowired
	BranchRepo BranchRepo;
	@Autowired
	ModelMapper modelMapper;

	public SubjectResponceDto addSubjects(SubjectRequestDto srd) {
		BranchModel branchModel = BranchRepo.findById(srd.getBranch_id())
				.orElseThrow(() -> new RuntimeException("Branch not found with id: " + srd.getBranch_id()));
		SubjectsModel subjectsModel = modelMapper.map(branchModel, SubjectsModel.class);
		subjectsModel.setSubject_name(srd.getSubject_name());
		subjectsModel.setBranchModel(branchModel);
		SubjectsModel saved = subjectRepo.save(subjectsModel);

		SubjectResponceDto response = modelMapper.map(saved, SubjectResponceDto.class);
		response.setBranch_id(branchModel.getBranch_id());
		response.setBranch_name(branchModel.getBranch_name());
		return response;

	}

	public List<SubjectResponceDto> getAllSubjects() {
		List<SubjectsModel> subjects = subjectRepo.findAll();
		List<SubjectResponceDto> responseDto = new ArrayList<>();

		for (SubjectsModel subject : subjects) {
			SubjectResponceDto response = modelMapper.map(subject, SubjectResponceDto.class);
			responseDto.add(response);

			if (subject.getBranchModel() != null) {
				response.setBranch_id(subject.getBranchModel().getBranch_id());
				response.setBranch_name(subject.getBranchModel().getBranch_name());

			}

		}
		return responseDto;

	}

	public SubjectResponceDto getById(Integer id) {
		SubjectsModel subjectsModel = subjectRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Subject not found with this ID :" + id));
		SubjectResponceDto subjectResponceDto = modelMapper.map(subjectsModel, SubjectResponceDto.class);
		return subjectResponceDto;
	}
	
	public SubjectResponceDto updateById(SubjectRequestDto srd,Integer id) {
		SubjectsModel existing=subjectRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Subject not found with this ID :" + id));
		existing.setSubject_name(srd.getSubject_name());
		System.out.println("DTO subject_name: " + srd.getSubject_name());
		SubjectsModel saved=subjectRepo.save(existing);
		
		SubjectResponceDto response=modelMapper.map(saved, SubjectResponceDto.class);
		
		return response;
	}
	
	
	public String deleteById(Integer id) {
		
		SubjectsModel existing =subjectRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Subject not found with this ID :" + id));
		subjectRepo.delete(existing);
		
		return "deleted sucessfully";
		
	}

}
