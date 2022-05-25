package com.hbsmoura.springcourse.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hbsmoura.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
	private Long id;
	private String subject;
	private String description;
	private Date creationDate;
	
	private RequestState state;
	private User user;
	private List<RequestStage> stages = new ArrayList<>();
}
