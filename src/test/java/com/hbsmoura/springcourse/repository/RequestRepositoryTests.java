package com.hbsmoura.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.domain.enums.RequestState;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RequestRepositoryTests {

	@Autowired
	private RequestRepository  requestRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Novo Laptop", "Pretendo obter um laptop", new Date(), RequestState.OPEN, owner, null);
		
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}

	@Test
	@Order(2)
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(1L, "Novo Laptop", "Pretendo obter um laptop com 16GB de RAM", null, RequestState.OPEN, owner, null);
		
		Request updatedRequest = requestRepository.save(request);
		
		assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop com 16GB de RAM");
	}

	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<Request> result = requestRepository.findById(1L);
		
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Novo Laptop");
	}

	@Test
	@Order(4)
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);
	}

	@Test
	@Order(5)
	public void listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	@Order(6)
	public void updateRequestStatusTest() {
		int affectdedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		assertThat(affectdedRows).isEqualTo(1);
	}
}
