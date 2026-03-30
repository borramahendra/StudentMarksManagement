package com.rolvatech.studentmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarksModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer marks_id;
	private Integer marks;
	@ManyToOne
	@JoinColumn(name="subject_id")
	private SubjectsModel subjectsModel;
	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentsModel studentsModel;

}
