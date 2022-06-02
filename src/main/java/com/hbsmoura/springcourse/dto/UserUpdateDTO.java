package com.hbsmoura.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.User;

import lombok.Data;

@Data
public class UserUpdateDTO {

	@NotBlank(message = "Name must not be blank")
	private String name;
	
	@Email
	private String email;
	
	@Size(min = 7, max = 99, message = "Password musr gave between 7 and 99 characters")
	private String password;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	public User turnIntoUser() {
		User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
		return user;
	}
}
