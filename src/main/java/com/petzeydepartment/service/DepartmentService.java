package com.petzeydepartment.service;

import java.util.List;

import com.petzeydepartment.entities.Department;
import com.petzeydepartment.exceptions.NoInputException;
import com.petzeydepartment.exceptions.ResourceNotFoundException;
import com.petzeydepartment.validation.DepartmentDto;

public interface DepartmentService {
	
	public Department addDepartment(DepartmentDto department);
	
	public DepartmentDto editDepartment(DepartmentDto departmentDto) throws ResourceNotFoundException, NoInputException;
	
	public List<Department>getAllDepartment() throws ResourceNotFoundException;
	
	public Department getDepartmentById(long departmentId) throws ResourceNotFoundException;
	
	public int getTotalNumberOfDepartments();
	
	public String getDepartmentMailById(long departmentId) throws ResourceNotFoundException;
	
	public String deleteDepartment(long departmentId) throws ResourceNotFoundException;
	
	public String getStatusById(long departmentId) throws ResourceNotFoundException;
	
	
	
	
	
}
