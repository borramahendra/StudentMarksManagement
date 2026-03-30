package com.rolvatech.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.StudentsModel;

@Repository
public interface StudentsRepo extends JpaRepository<StudentsModel, Integer> {

}
