package com.hbsmoura.springcourse.dto;

import com.hbsmoura.springcourse.domain.enums.Role;

import lombok.Data;

@Data
public class UserUpdateRoleDTO {

	private Role role;
}
