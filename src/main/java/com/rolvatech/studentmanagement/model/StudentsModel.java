package com.rolvatech.studentmanagement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "college_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer student_id;
	private String Student_name;
	@Column(name="student_email")
	private String studentEmail;
	private String studentPassword;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private BranchModel branchModel;
	@OneToMany(mappedBy = "studentsModel",cascade = CascadeType.ALL,orphanRemoval = true)
	List<MarksModel> marks;
	@OneToMany(mappedBy = "studentsModel",cascade = CascadeType.ALL,orphanRemoval = true)
	List<EmailModel> emails;

}
