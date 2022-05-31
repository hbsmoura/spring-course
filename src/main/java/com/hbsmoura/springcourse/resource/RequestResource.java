package com.hbsmoura.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.service.RequestService;
import com.hbsmoura.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		Request created = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
		request.setId(id);
		Request updated = requestService.save(request);
		return ResponseEntity.ok(updated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> list() {
		List<Request> list = requestService.list();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("{id}/stages")
	public ResponseEntity<List<RequestStage>> listStagesById(@PathVariable(name = "id") Long id){
		List<RequestStage> list = requestStageService.listRequestById(id);
		return ResponseEntity.ok(list);
	}
}
