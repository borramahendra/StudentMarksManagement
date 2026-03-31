package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequestDto {
	private Integer branch_id;
	private String branch_name;
	
	private Integer college_id;
	private String college_Name;

}
