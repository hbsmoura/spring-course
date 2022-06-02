package com.hbsmoura.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.hbsmoura.springcourse.domain.enums.Role;

import lombok.Data;

@Data
public class UserUpdateRoleDTO {

	@NotNull(message = "Role required")
	private Role role;
}
