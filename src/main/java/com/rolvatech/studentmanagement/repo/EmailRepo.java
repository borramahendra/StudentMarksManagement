package com.rolvatech.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.EmailModel;

@Repository
public interface EmailRepo extends JpaRepository<EmailModel, Integer>{

}
