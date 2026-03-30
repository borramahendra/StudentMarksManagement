package com.rolvatech.studentmanagement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "college_Name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer college_id;
	private String college_Name;
	private String college_location;
	@OneToMany(mappedBy = "collegeModel",cascade = CascadeType.ALL) // ✅ collection
	private List<BranchModel> branches;

}
