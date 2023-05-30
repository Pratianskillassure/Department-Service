

package com.petzeydepartment.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName="build")
@AllArgsConstructor
@Data
public class DepartmentDto {
	
	@NotNull(message="department name should not be null")
	@Size(min=5,message="department name is too short")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid department name")
	private String departmentName;
	
	@Size(min=10,message="description should contain atleast 10 characters")
	private String description;
	
	private boolean status;
	
	
	@Email(message="enter valid email id")
	private String departmentEmail;
	
	private long departmentId;
}
