package com.petzeydepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petzeydepartment.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query(value = "Select d from Department d where d.departmentId=:id")
	public Department getById(@Param(value = "id") long id);

}
