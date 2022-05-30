package com.hbsmoura.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.enums.RequestState;
import com.hbsmoura.springcourse.repository.RequestRepository;
import com.hbsmoura.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		RequestStage createdStage = requestStageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		
		return createdStage;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> stage = requestStageRepository.findById(id);
		return stage.get();
	}
	
	public List<RequestStage> listRequestById(Long id) {
		return requestStageRepository.findAllByRequestId(id);
	}
	
}
