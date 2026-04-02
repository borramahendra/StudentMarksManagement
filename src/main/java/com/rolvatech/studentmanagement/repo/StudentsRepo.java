package com.rolvatech.studentmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.StudentsModel;

@Repository
public interface StudentsRepo extends JpaRepository<StudentsModel, Integer> {
	
	@Query("SELECT s FROM StudentsModel s WHERE LOWER(s.studentEmail) = LOWER(:email)")
	Optional<StudentsModel> findByStudentEmail(@Param("email") String email);
	//Optional<StudentsModel> findByStudentEmail(String studentEmail);
	
	

}
