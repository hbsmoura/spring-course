package com.hbsmoura.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStageSaveDTO {

	private String description;
	
	@NotNull(message = "State required")
	private RequestState state;
	
	@NotNull(message = "Request required")
	private Request request;
	
	@NotNull(message = "Owner required")
	private User owner;
	
	public RequestStage turnIntoRequestStage() {
		return new RequestStage(null, null, description, state, request, owner);
	}
}
