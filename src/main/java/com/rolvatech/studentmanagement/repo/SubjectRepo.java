package com.rolvatech.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.SubjectsModel;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectsModel, Integer>{

}
