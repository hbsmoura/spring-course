package com.hbsmoura.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.enums.RequestState;
import com.hbsmoura.springcourse.exception.NotFoundException;
import com.hbsmoura.springcourse.model.PageModel;
import com.hbsmoura.springcourse.model.PageRequestModel;
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
		return result.orElseThrow(() -> new NotFoundException("There is no request with this Id = " + id));
	}
	
	public List<Request> list(){
		return requestRepository.findAll();
	}
	
	public PageModel<Request> listOnLazyModel(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAll(pageable);
		PageModel<Request> pm = new PageModel<>(
				(int)page.getTotalElements(), page.getSize(),
				page.getTotalPages(), page.getContent());
		return pm;
	}
	
	public List<Request> listByOwnerId(Long id){
		return requestRepository.findAllByOwnerId(id);
	}
	
	public PageModel<Request> listByOwnerIdOnLazyModel(Long id, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(id, pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
