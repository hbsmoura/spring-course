package com.hbsmoura.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.RequestStage;
import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.domain.enums.RequestState;

@SpringBootTest
public class RequestStageRepositoryTests {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		Request request = new Request();
		request.setId(1L);
		User owner = new User();
		owner.setId(1L);
		
		RequestStage requestStage = new RequestStage(null, new Date(), "Foi comprado um novo laptop com 16GB de RAM", RequestState.CLOSED, request, owner);
		
		RequestStage createdStage = requestStageRepository.save(requestStage);
		
		assertThat(createdStage.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stage = result.get();
		
		assertThat(stage.getDescription()).isEqualTo("Foi comprado um novo laptop com 16GB de RAM");
	}
	
	@Test
	@Order(3)
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertThat(stages.size()).isEqualTo(1);
	}
}
