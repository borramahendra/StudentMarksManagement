package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarksResponseDto {
	
	private Integer marks_id;
	private Integer marks;
	private String Student_name;
	private String Subject_name;

}
