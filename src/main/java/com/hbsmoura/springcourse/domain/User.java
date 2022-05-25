package com.hbsmoura.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	
	private List<Request> requests = new ArrayList<>();
	private List<RequestStage> stages = new ArrayList<>();
}
