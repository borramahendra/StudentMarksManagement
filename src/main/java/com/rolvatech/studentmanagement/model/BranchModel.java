package com.rolvatech.studentmanagement.model;

import java.util.List;
import jakarta.persistence.CascadeType;
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
@Table(name = "College_branches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer branch_id;
	private String branch_name;
	@ManyToOne
	@JoinColumn(name = "college_id")
	private CollegeModel collegeModel;
	@OneToMany(mappedBy = "branchModel", cascade = CascadeType.ALL)
	List<StudentsModel> students;

}
