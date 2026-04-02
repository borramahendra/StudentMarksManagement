package com.rolvatech.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.dto.LoginRequestDto;
import com.rolvatech.studentmanagement.dto.StudentRequestDto;
import com.rolvatech.studentmanagement.dto.StudentResponseDto;
import com.rolvatech.studentmanagement.model.BranchModel;
import com.rolvatech.studentmanagement.model.StudentsModel;
import com.rolvatech.studentmanagement.repo.BranchRepo;
import com.rolvatech.studentmanagement.repo.StudentsRepo;

@Service
public class StudentService {
	@Autowired
	StudentsRepo studentsRepo;

	@Autowired
	BranchRepo BranchRepo;

	@Autowired
	ModelMapper modelMapper;

	public StudentResponseDto addStudents(StudentRequestDto srd) {

		BranchModel branchModel = BranchRepo.findById(srd.getBranch_id())
				.orElseThrow(() -> new RuntimeException("Branch not found with this id: " + srd.getBranch_id()));

		StudentsModel studentsModel = modelMapper.map(branchModel, StudentsModel.class);
		studentsModel.setStudent_name(srd.getStudent_name());
		studentsModel.setStudentEmail(srd.getStudentEmail());
		studentsModel.setBranchModel(branchModel);
		StudentsModel saved = studentsRepo.save(studentsModel);

		StudentResponseDto response = modelMapper.map(saved, StudentResponseDto.class);
		response.setBranch_id(branchModel.getBranch_id());
		response.setBranch_name(branchModel.getBranch_name());

		return response;
	}

	public List<StudentResponseDto> getAllStudents() {

		List<StudentsModel> students = studentsRepo.findAll();
		List<StudentResponseDto> studentsDto = new ArrayList<>();

		for (StudentsModel student : students) {
			StudentResponseDto response = modelMapper.map(student, StudentResponseDto.class);
			studentsDto.add(response);

			if (student.getBranchModel() != null) {
				response.setBranch_id(student.getBranchModel().getBranch_id());
				response.setBranch_name(student.getBranchModel().getBranch_name());

			}
		}
		return studentsDto;
	}

	public StudentResponseDto getById(Integer id) {
		StudentsModel studentsModel = studentsRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found with this ID : " + id));
		StudentResponseDto response = modelMapper.map(studentsModel, StudentResponseDto.class);
		return response;
	}
	
	public StudentResponseDto updateById(StudentRequestDto srd,Integer id) {
		
		StudentsModel existing=studentsRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found with this ID : " + id));
		existing.setStudent_name(srd.getStudent_name());
		existing.setStudentEmail(srd.getStudentEmail());
		existing.setStudentPassword(srd.getStudentPassword());
		StudentsModel saved=studentsRepo.save(existing);
		
		StudentResponseDto response=modelMapper.map(saved, StudentResponseDto.class);
		
		return response;
		
	}
	
	public String deleteById(Integer id) {
		StudentsModel existing =studentsRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found with this ID : " + id));
		studentsRepo.delete(existing);
		
		return "deleted sucessfully";		
	}
	
	public String login(LoginRequestDto request) {
		StudentsModel student =studentsRepo
				.findByStudentEmail(request.getStudentEmail())
				.orElseThrow(() -> new RuntimeException("Invalid Email"));

		if(!student.getStudentPassword().equals(request.getStudentPassword())) {
		    throw new RuntimeException("Invalid Password");
			
		}
		
		return "login sucessfully";
		
	}

}
