package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeResponceDto {
	private Integer college_id;
	private String college_Name;
	private String college_location;

}
