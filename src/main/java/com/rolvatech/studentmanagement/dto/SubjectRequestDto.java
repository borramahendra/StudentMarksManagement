package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDto {
	private Integer subject_id;
	private String Subject_name;
	private Integer branch_id;
	private String branch_name;

}
