package com.petzeydepartment;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.petzeydepartment.entities.Department;

import com.petzeydepartment.exceptions.NoInputException;

import com.petzeydepartment.exceptions.ResourceNotFoundException;

import com.petzeydepartment.repository.DepartmentRepository;

import com.petzeydepartment.serviceimpl.DepartmentServiceImpl;

import com.petzeydepartment.validation.DepartmentDto;

@SpringBootTest


public class PetzeyDepartmentServiceImplTest {

	@Mock

	private DepartmentRepository departmentRepository;

	@InjectMocks

	private DepartmentServiceImpl departmentService;

	@Test

	public void insertDepartmentTest() {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 1);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.save(department)).thenReturn(department);

		assertEquals(department, departmentService.addDepartment(departmentDto));

	}

	@Test

	public void getDepartmentListTest() throws ResourceNotFoundException {

		DepartmentDto departmentDto1 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 1);

		Department department1 = Department.build(departmentDto1.getDepartmentId(), departmentDto1.getDepartmentName(),
				departmentDto1.getDescription(), departmentDto1.isStatus(), departmentDto1.getDepartmentEmail());

		DepartmentDto departmentDto2 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 2);

		Department department2 = Department.build(departmentDto2.getDepartmentId(), departmentDto2.getDepartmentName(),
				departmentDto2.getDescription(), departmentDto2.isStatus(), departmentDto2.getDepartmentEmail());

		DepartmentDto departmentDto3 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 3);

		Department department3 = Department.build(departmentDto3.getDepartmentId(), departmentDto3.getDepartmentName(),
				departmentDto3.getDescription(), departmentDto3.isStatus(), departmentDto3.getDepartmentEmail());

		List<Department> lists = new ArrayList<>();

		lists.add(department3);

		lists.add(department2);

		lists.add(department1);

		when(departmentRepository.findAll()).thenReturn(lists);

		assertEquals(3, departmentService.getAllDepartment().size());

	}

	@Test

	public void editDepartmentTest() throws ResourceNotFoundException, NoInputException {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 1);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.save(department)).thenReturn(department);

		departmentDto.setDepartmentEmail("heart@gmail.com");

		Department department1 = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.getById(1L)).thenReturn(department1);

		assertEquals("heart@gmail.com", departmentService.editDepartment(departmentDto).getDepartmentEmail());

	}

	@Test

	public void getDepartmentByIdTest() throws ResourceNotFoundException {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 1L);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.getById(1L)).thenReturn(department);

		Department department1 = departmentService.getDepartmentById(1L);

		assertEquals(department, department1);

	}

	@Test

	public void getDepartmentStatusByIdTest() {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 3L);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.getById(3L)).thenReturn(department);

		String message = departmentService.getStatusById(3L);

		assertEquals("Inactive", message);

	}

	@Test

	public void getDepartmentMailByIdTest() {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 3L);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.getById(3L)).thenReturn(department);

		String message = departmentService.getDepartmentMailById(3L);

		assertEquals(department.getDepartmentEmail(), message);

	}

	@Test

	public void deleteDepartmentByIdTest() {

		DepartmentDto departmentDto = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 3L);

		Department department = Department.build(departmentDto.getDepartmentId(), departmentDto.getDepartmentName(),
				departmentDto.getDescription(), departmentDto.isStatus(), departmentDto.getDepartmentEmail());

		when(departmentRepository.getById(3L)).thenReturn(department);

		assertEquals("deleted successfully!!!!", departmentService.deleteDepartment(3L));

	}

	@Test

	public void getTotalNoOfDepartmentsTest() {

		DepartmentDto departmentDto1 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 1);

		Department department1 = Department.build(departmentDto1.getDepartmentId(), departmentDto1.getDepartmentName(),
				departmentDto1.getDescription(), departmentDto1.isStatus(), departmentDto1.getDepartmentEmail());

		DepartmentDto departmentDto2 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 2);

		Department department2 = Department.build(departmentDto2.getDepartmentId(), departmentDto2.getDepartmentName(),
				departmentDto2.getDescription(), departmentDto2.isStatus(), departmentDto2.getDepartmentEmail());

		DepartmentDto departmentDto3 = new DepartmentDto("Cardiodiagnosis", "Heart realted Test", false,
				"cardio@gmail.com", 3);

		Department department3 = Department.build(departmentDto3.getDepartmentId(), departmentDto3.getDepartmentName(),
				departmentDto3.getDescription(), departmentDto3.isStatus(), departmentDto3.getDepartmentEmail());

		List<Department> lists = new ArrayList<>();

		lists.add(department3);

		lists.add(department2);

		lists.add(department1);

		when(departmentRepository.findAll()).thenReturn(lists);

		assertEquals(lists.size(), departmentService.getTotalNumberOfDepartments());

	}

}