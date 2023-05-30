package com.petzeydepartment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petzeydepartment.entities.Department;
import com.petzeydepartment.exceptions.NoInputException;
import com.petzeydepartment.exceptions.ResourceNotFoundException;
import com.petzeydepartment.serviceimpl.DepartmentServiceImpl;
import com.petzeydepartment.validation.DepartmentDto;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/petzeydepartment")
public class Controller {
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	//Adding new Department
	@PostMapping("/department/create")
	public ResponseEntity<?>post(@RequestBody @Valid DepartmentDto departmentDto){
		
		ResponseEntity<?>response=new ResponseEntity<>(departmentService.addDepartment(departmentDto),HttpStatus.CREATED);
		
		return response;
	}
	
	//Editing existing Department
	@PutMapping("/department/edit")
	public ResponseEntity<?>put(@RequestBody @Valid DepartmentDto departmentDto){
		
		ResponseEntity<?>response;
		
		response=new ResponseEntity<>(departmentService.editDepartment(departmentDto),HttpStatus.OK);
		
		
		return response;
		
	}
	
	// To Get Department By DepartmentID
	@GetMapping("/department/departmentbyid/{id}")
	public ResponseEntity<?>getById(@PathVariable(value="id") long id){
		ResponseEntity<?>response;
		response=new ResponseEntity<>(departmentService.getDepartmentById(id),HttpStatus.OK);
		
		return response;
	}
	//To Get All the Existing Department
	@GetMapping("/department/alldepartments")
	public ResponseEntity<?>getAllDepartments(){
		ResponseEntity<?>response;
	
		response=new ResponseEntity<>(departmentService.getAllDepartment(),HttpStatus.OK);
		
		return response;
	}
	//Getting Department Email by departmentId
	@GetMapping("/department/departmentmailbyid/{id}")
	public ResponseEntity<?>getDepartmentMailById(@PathVariable(value="id") long id){
		ResponseEntity<?>response;
		
		response=new ResponseEntity<>(departmentService.getDepartmentMailById(id),HttpStatus.OK);
		
		
		return response;
	}
	//To get the Total departments in Database
	@GetMapping("/department/totaldepartments")
	public ResponseEntity<?>totalNumberOfDepartments(){
		ResponseEntity<?>response;
		response=new ResponseEntity<>(departmentService.getTotalNumberOfDepartments(),HttpStatus.OK);
		
		return response;
	}
	
	//To delete Department By DepartmentId
	@DeleteMapping("/department/delete/{id}")
	public ResponseEntity<?>deleteDepartment(@PathVariable(value="id") long id){
		ResponseEntity<?>response;
		
		response=new ResponseEntity<>(departmentService.deleteDepartment(id),HttpStatus.OK);
		
		
		return response;
	}
	
	//To get the department status by department Id
	@GetMapping("/department/departmentstatus/{id}")
	public ResponseEntity<?>departmentStatus(@PathVariable(value="id") long id){
		ResponseEntity<?>response;
		
		response=new ResponseEntity<>(departmentService.getStatusById(id),HttpStatus.OK);
		
		
		return response;
	}
	
}
