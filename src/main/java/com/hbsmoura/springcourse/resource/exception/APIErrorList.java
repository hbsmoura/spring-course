package com.hbsmoura.springcourse.resource.exception;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class APIErrorList extends APIError {
	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public APIErrorList(int code, String msg, Date date, List<String> list) {
		super(code, msg, date);
		this.errors = list;
	}

}
