package com.hospital.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.system.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
     boolean existsByName(String name);





}
