package com.hbsmoura.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.dto.RequestStageSaveDTO;
import com.hbsmoura.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {

	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDTO stageDTO) {
		RequestStage stage = stageDTO.turnIntoRequestStage();
		RequestStage created = requestStageService.save(stage);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		RequestStage stage = requestStageService.getById(id);
		return ResponseEntity.ok(stage);
	}
}
