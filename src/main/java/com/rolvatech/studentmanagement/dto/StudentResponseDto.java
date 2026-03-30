package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
	private String Student_name;
	private String Student_email;
	
	private Integer branch_id;
	private String branch_name;

}
