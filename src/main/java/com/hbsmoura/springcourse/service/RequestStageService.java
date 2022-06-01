package com.hbsmoura.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.enums.RequestState;
import com.hbsmoura.springcourse.exception.NotFoundException;
import com.hbsmoura.springcourse.model.PageModel;
import com.hbsmoura.springcourse.model.PageRequestModel;
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
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There is no request stage with this Id = " + id));
	}
	
	public List<RequestStage> listByRequestId(Long id){
		return requestStageRepository.findAllByRequestId(id);
	}
	
	public PageModel<RequestStage> listByRequestId(Long id, PageRequestModel pr) {
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = requestStageRepository.findAllByRequestId(id, pageable);
		PageModel<RequestStage> pm = new PageModel<>(
				(int)page.getTotalElements(), page.getSize(),
				page.getTotalPages(), page.getContent());
		return pm;
	}
	
}
