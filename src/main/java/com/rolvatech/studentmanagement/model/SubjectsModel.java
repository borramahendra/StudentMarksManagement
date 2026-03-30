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
@Table(name = "branch_subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subject_id;
	private String Subject_name;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private BranchModel branchModel;
	@OneToMany(mappedBy = "subjectsModel",cascade = CascadeType.ALL)
	List<MarksModel> marks;

}
