package com.rolvatech.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolvatech.studentmanagement.model.BranchModel;

@Repository
public interface BranchRepo extends JpaRepository<BranchModel, Integer> {

}
