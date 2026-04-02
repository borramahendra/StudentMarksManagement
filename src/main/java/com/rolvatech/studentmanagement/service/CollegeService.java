package com.rolvatech.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.dto.CollegeRequestDto;
import com.rolvatech.studentmanagement.dto.CollegeResponceDto;
import com.rolvatech.studentmanagement.model.CollegeModel;
import com.rolvatech.studentmanagement.repo.CollegeRepo;

@Service
public class CollegeService {
	@Autowired
	CollegeRepo collegeRepo;

	@Autowired
	ModelMapper modelMapper;

	public CollegeResponceDto addCollege(CollegeRequestDto crd) {
		CollegeModel collegeModel = modelMapper.map(crd, CollegeModel.class);
		CollegeModel saved = collegeRepo.save(collegeModel);
		CollegeResponceDto responce = modelMapper.map(saved, CollegeResponceDto.class);
		return responce;

	}

	public List<CollegeResponceDto> getAllColleges() {

		List<CollegeModel> colleges = collegeRepo.findAll();

		List<CollegeResponceDto> responceDto = new ArrayList<>();

		for (CollegeModel college : colleges) {
			CollegeResponceDto dto = modelMapper.map(college, CollegeResponceDto.class);
			responceDto.add(dto);
		}

		return responceDto;

	}

	public CollegeResponceDto getById(Integer id) {

		CollegeModel college = collegeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("College not found with this id : " + id));;
		CollegeResponceDto responce = modelMapper.map(college, CollegeResponceDto.class);

		return responce;

	}

	public CollegeResponceDto updateById(CollegeRequestDto crd, Integer id) {
		CollegeModel existing = collegeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("College not found with this id : " + id));

		existing.setCollege_Name(crd.getCollege_Name());
		existing.setCollege_location(crd.getCollege_location());

		CollegeModel updated = collegeRepo.save(existing);

		CollegeResponceDto response = modelMapper.map(updated, CollegeResponceDto.class);

		return response;

	}

	public String deleteById(Integer id) {
		CollegeModel existing = collegeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("College not found with this id : " + id));

		collegeRepo.delete(existing);
		return "deleted sucessfully";

	}

}
