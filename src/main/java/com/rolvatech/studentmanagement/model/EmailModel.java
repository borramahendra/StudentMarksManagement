package com.rolvatech.studentmanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="EmailSchdule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer email_id;
	private String  toEmail;
	private String subject;
	private String text;
	private LocalDateTime schdule;
	private boolean status=false;
	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentsModel studentsModel;
	

}
