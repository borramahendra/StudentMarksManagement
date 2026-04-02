package com.rolvatech.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarksRequestdto {
	private Integer marks_id;
	private Integer marks;
	private Integer student_id;
	private Integer subject_id;

}
