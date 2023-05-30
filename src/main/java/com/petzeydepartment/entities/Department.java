package com.petzeydepartment.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName="build")
@NoArgsConstructor
@Data	
@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long departmentId;
	
	private String departmentName;
	
	private String description;
	
	private boolean status;
	
	private String departmentEmail;
	
}
