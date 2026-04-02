package com.rolvatech.studentmanagement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailScheduleRequestDto {
	
	private Integer student_id;
	private LocalDateTime schdule;

}
