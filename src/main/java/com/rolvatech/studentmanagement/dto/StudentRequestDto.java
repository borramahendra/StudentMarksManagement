package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
	private String Student_name;
	private String studentEmail;
	private String studentPassword;
	
	private Integer branch_id;
	private String branch_name;

}
