package com.hbsmoura.springcourse.domain;

import java.util.Date;

import com.hbsmoura.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestStage {
	private Long id;
	private Date realizationDate;
	private String description;
	
	private RequestState state;
	private Request request;
	private User user;
}
