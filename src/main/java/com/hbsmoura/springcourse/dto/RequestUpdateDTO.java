package com.hbsmoura.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
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
public class RequestUpdateDTO {

	@NotBlank(message = "Subject must not be blank")
	private String subject;
	
	private String description;
	
	@NotNull(message = "State required")
	private RequestState state;
	
	@NotNull(message = "Owner required")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<>();
	
	public Request turnIntoRequest() {
		Request request = new Request(null, this.subject, this.description, null, this.state, this.owner, this.stages);
		return request;
	}
}
