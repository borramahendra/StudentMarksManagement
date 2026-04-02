package com.rolvatech.studentmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.MarksModel;
import com.rolvatech.studentmanagement.model.StudentsModel;

@Repository
public interface MarksRepo extends JpaRepository<MarksModel, Integer> {
	
	List<MarksModel> findAllMarksByStudentsModel(StudentsModel student);

}
