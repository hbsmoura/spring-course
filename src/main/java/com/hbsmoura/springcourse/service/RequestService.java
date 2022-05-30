package com.hbsmoura.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.enums.RequestState;
import com.hbsmoura.springcourse.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		return requestRepository.save(request);
	}
	
	public Request update(Request request) {
		return requestRepository.save(request);
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}
	
	public List<Request> list(){
		return requestRepository.findAll();
	}
	
	public List<Request> listByOwnerId(Long id){
		return requestRepository.findAllByOwnerId(id);
	}
	
	//updateRequestStatus
}
