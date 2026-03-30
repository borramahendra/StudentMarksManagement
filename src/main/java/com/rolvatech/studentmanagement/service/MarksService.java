package com.rolvatech.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.dto.MarksRequestdto;
import com.rolvatech.studentmanagement.dto.MarksResponseDto;
import com.rolvatech.studentmanagement.model.MarksModel;
import com.rolvatech.studentmanagement.model.StudentsModel;
import com.rolvatech.studentmanagement.model.SubjectsModel;
import com.rolvatech.studentmanagement.repo.MarksRepo;
import com.rolvatech.studentmanagement.repo.StudentsRepo;
import com.rolvatech.studentmanagement.repo.SubjectRepo;

@Service
public class MarksService {
	@Autowired
	MarksRepo marksRepo;

	@Autowired
	SubjectRepo subjectRepo;

	@Autowired
	StudentsRepo studentsRepo;

	@Autowired
	ModelMapper modelMapper;

	public MarksResponseDto addMarks(MarksRequestdto mrd) {
		StudentsModel student = studentsRepo.findById(mrd.getStudent_id())
				.orElseThrow(() -> new RuntimeException("College not found with id: " + mrd.getStudent_id()));

		SubjectsModel subject = subjectRepo.findById(mrd.getSubject_id())
				.orElseThrow(() -> new RuntimeException("College not found with id: " + mrd.getSubject_id()));
		
		MarksModel mark = modelMapper.map(mrd, MarksModel.class);
		
		mark.setMarks_id(null);
		mark.setStudentsModel(student);
		mark.setSubjectsModel(subject);

		MarksModel saved = marksRepo.save(mark);

		MarksResponseDto response = modelMapper.map(saved, MarksResponseDto.class);
		response.setMarks(saved.getMarks());
		response.setStudent_name(student.getStudent_name());
		response.setSubject_name(subject.getSubject_name());
		return response;
	}

	public List<MarksResponseDto> getAllMarks() {

		List<MarksModel> marksModel = marksRepo.findAll();

		List<MarksResponseDto> marksDto = new ArrayList<>();
		for (MarksModel marks : marksModel) {
			MarksResponseDto response = modelMapper.map(marks, MarksResponseDto.class);
			marksDto.add(response);

			if (marks.getStudentsModel() != null && marks.getSubjectsModel() != null) {
				response.setStudent_name(marks.getStudentsModel().getStudent_name());
				response.setSubject_name(marks.getSubjectsModel().getSubject_name());

			}

		}
		return marksDto;

	}

	public MarksResponseDto getById(int id) {
		MarksModel marksModel = marksRepo.findById(id).orElseThrow();
		MarksResponseDto marksResponseDto = modelMapper.map(marksModel, MarksResponseDto.class);
		marksResponseDto.setStudent_name(marksModel.getStudentsModel().getStudent_name());
		marksResponseDto.setSubject_name(marksModel.getSubjectsModel().getSubject_name());

		return marksResponseDto;
	}

}
