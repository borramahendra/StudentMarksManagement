package com.rolvatech.studentmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.dto.MarksRequestdto;
import com.rolvatech.studentmanagement.dto.MarksResponseDto;
import com.rolvatech.studentmanagement.model.EmailModel;
import com.rolvatech.studentmanagement.model.MarksModel;
import com.rolvatech.studentmanagement.model.StudentsModel;
import com.rolvatech.studentmanagement.model.SubjectsModel;
import com.rolvatech.studentmanagement.repo.EmailRepo;
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
	EmailRepo emailRepo;

	@Autowired
	StudentsRepo studentsRepo;

	@Autowired
	EmailService emailService;

	@Autowired
	ModelMapper modelMapper;

	public MarksResponseDto addMarks(MarksRequestdto mrd) {
		StudentsModel student = studentsRepo.findById(mrd.getStudent_id())
				.orElseThrow(() -> new RuntimeException("student not found with id: " + mrd.getStudent_id()));

		SubjectsModel subject = subjectRepo.findById(mrd.getSubject_id())
				.orElseThrow(() -> new RuntimeException("subject not found with id: " + mrd.getSubject_id()));

		MarksModel mark = modelMapper.map(mrd, MarksModel.class);

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

	public MarksResponseDto getById(Integer id) {
		MarksModel marksModel = marksRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("With this ID no Marks are Present :" + id));
		MarksResponseDto marksResponseDto = modelMapper.map(marksModel, MarksResponseDto.class);
		marksResponseDto.setStudent_name(marksModel.getStudentsModel().getStudent_name());
		marksResponseDto.setSubject_name(marksModel.getSubjectsModel().getSubject_name());

		return marksResponseDto;
	}

	public MarksResponseDto updateById(MarksRequestdto mrd, Integer id) {
		MarksModel existing = marksRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("With this ID no Marks are Present :" + id));
		existing.setMarks(mrd.getMarks());

		MarksModel saved = marksRepo.save(existing);

		MarksResponseDto response = modelMapper.map(saved, MarksResponseDto.class);
		response.setStudent_name(saved.getStudentsModel().getStudent_name());
		response.setSubject_name(saved.getSubjectsModel().getSubject_name());

		return response;

	}

	public String deleteById(Integer id) {

		MarksModel existing = marksRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("With this ID no Marks are Present :" + id));
		marksRepo.delete(existing);

		return "deleted sucessfully";

	}

	public String marksReportCard(Integer id, LocalDateTime schdule) {

		StudentsModel student = studentsRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("student ID is not found : " + id));
		List<MarksModel> marksList = marksRepo.findAllMarksByStudentsModel(student);

		StringBuilder body = new StringBuilder();

		body.append("Hello  ").append(student.getStudent_name()).append(",\n\n");

		body.append("Here is your Marks report Card : \n\n");
		int total = 0;
		boolean isFail = false;
		for (MarksModel marks : marksList) {

			String subjectName = marks.getSubjectsModel().getSubject_name();
			int mark = marks.getMarks();

			body.append(subjectName).append(" : ").append(mark).append("-->");
			if (mark < 35) {
				body.append(" (Fail)");
				isFail = true;
			} else {
				body.append(" (Pass)");
			}
			body.append("\n");

			total += mark;

		}

		double average = 0;
		if (!marksList.isEmpty()) {
			average = (double) total / marksList.size();

		}

		String result;

		if (isFail) {
			result = "FAIL";
		} else {
			result = "PASS";
		}

		body.append("\nTotal Marks : ").append(total).append("\nAverage : ").append(average).append("\nResult : ")
				.append(result);

		body.append("\n\n Best Regards,").append("\n College Management.");

		EmailModel em = new EmailModel();

		em.setToEmail(student.getStudentEmail());
		em.setSubject("Marks Report card");
		em.setText(body.toString());
		em.setStudentsModel(student);

		em.setSchdule(schdule);

		emailRepo.save(em);

		return "Marks report send sucessfully";
	}

	public String sendMailToAllStudents(LocalDateTime schdule) {
		List<StudentsModel> students = studentsRepo.findAll();

		for (StudentsModel student : students) {

			marksReportCard(student.getStudent_id(), schdule);
		}

		return "Marks report send sucessfully to all the students";
	}

}
