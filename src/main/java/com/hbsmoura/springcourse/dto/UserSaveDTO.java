package com.hbsmoura.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.domain.enums.Role;

import lombok.Data;

@Data
public class UserSaveDTO {

	@NotBlank(message = "Name must not be blank")
	private String name;
	
	@Email
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must have between 7 and 99 characters")
	private String password;
	
	@NotNull(message = "Role required")
	private Role role;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
	
	public User turnIntoUser() {
		User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
		return user;
	}
}
