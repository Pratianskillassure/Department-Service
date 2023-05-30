package com.petzeydepartment.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petzeydepartment.entities.Department;
import com.petzeydepartment.exceptions.NoInputException;
import com.petzeydepartment.exceptions.ResourceNotFoundException;
import com.petzeydepartment.repository.DepartmentRepository;
import com.petzeydepartment.service.DepartmentService;
import com.petzeydepartment.validation.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	//to post the department values to db
	@Override
	public Department addDepartment(DepartmentDto departmentDto) {
		log.info("Adding new department");
		//copying values from dto to orinal department
		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(), departmentDto.getDescription(),
				departmentDto.isStatus(), departmentDto.getDepartmentEmail());
		
		log.info("saving new department");
		departmentRepository.save(department);
		return department;
	}
	
	//to edit department values
	@Override
	public DepartmentDto editDepartment(DepartmentDto departmentDto)
			throws ResourceNotFoundException, NoInputException {
		log.info("editing department");
		if (Long.valueOf(departmentDto.getDepartmentId()) == 0) {
			log.error("department id not entered");
			throw new NoInputException("Enter a department Id: ");

		}
		log.info("getting department of id " + departmentDto.getDepartmentId());
		Department departmentFromRepo = getDepartmentById(departmentDto.getDepartmentId());

		if (departmentFromRepo == null) {
			log.error("department with given id " + departmentDto.getDepartmentId() + " does not exist");
			throw new ResourceNotFoundException(
					"department with given id " + departmentDto.getDepartmentId() + " not found");

		}
		log.info("updating department");
		departmentFromRepo.setDepartmentName(departmentDto.getDepartmentName());
		departmentFromRepo.setStatus(departmentDto.isStatus());
		departmentFromRepo.setDescription(departmentDto.getDescription());
		departmentFromRepo.setDepartmentEmail(departmentDto.getDepartmentEmail());

		log.info("saving edited department");
		departmentRepository.save(departmentFromRepo);

		
		
		log.info("saved edited department");
		return departmentDto;
	}
	
	//to get the list of departments
	@Override
	public List<Department> getAllDepartment() throws ResourceNotFoundException {
		log.info("fetching all department");
		List<Department> departmentsList = departmentRepository.findAll();

		if (departmentsList.size() == 0) {
			log.error("no department exist in records");
			throw new ResourceNotFoundException("no department exists in records");
		}

		return departmentsList;

	}
	
	//getting department by id
	@Override
	public Department getDepartmentById(long departmentId) throws ResourceNotFoundException {
		log.info("getting department of id " + departmentId);
		Department departmentFromRepo = departmentRepository.getById(departmentId);

		if (departmentFromRepo == null)
			throw new ResourceNotFoundException("department with given id " + departmentId + " not found");
//		if(departmentFromRepo==null)
//			throw new ResourceNotFoundException("department with given id "+departmentId+" not found");
		log.info("returning department of id " + departmentId);
		return departmentFromRepo;
	}
	
	//to get the total number department that exist in db
	@Override
	public int getTotalNumberOfDepartments() {
		log.info("getting all departments");
		List<Department> departmentsList = departmentRepository.findAll();
		return departmentsList.size();
		
	}

	// To get Department EmailId by Department Id 
	@Override
	public String getDepartmentMailById(long departmentId) throws ResourceNotFoundException {
		Department departmentFromRepo = getDepartmentById(departmentId);
		if (departmentFromRepo == null) {
			log.error("department with given id " + departmentId + " does not exist");
			throw new ResourceNotFoundException("department with given id " + departmentId + " not found");
		}
		return departmentFromRepo.getDepartmentEmail();
	}

	//TO delete department by department Id
	@Override
	public String deleteDepartment(long departmentId) throws ResourceNotFoundException {
		Department departmentFromRepo = getDepartmentById(departmentId);
		log.info("deleting department with id" + departmentId);
		departmentRepository.deleteById(departmentFromRepo.getDepartmentId());
		log.info("department with id" + departmentId + "deleted successfully");
		return "deleted successfully!!!!";

	}
	//to get the department status by department id
	@Override
	public String getStatusById(long departmentId) throws ResourceNotFoundException {
		Department departmentFromRepo = getDepartmentById(departmentId);
		log.info("getting department status");
		String message = (departmentFromRepo.isStatus()) ? "Active" : "Inactive";
		return message;
	}

}
