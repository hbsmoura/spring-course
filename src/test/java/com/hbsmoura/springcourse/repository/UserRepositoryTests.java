package com.hbsmoura.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.domain.enums.Role;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)
	public void saveTest() {
		User user = new User(null, "Tupac", "2pac@gamil.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void updateTest() {
		User user = new User(1L, "Tupac Shakur", "2pac@gamil.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("Tupac Shakur");
	}
	
	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("123");
	}
	
	@Test
	@Order(4)
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	@Order(5)
	public void loginTest() {
		Optional<User> result = userRepository.login("2pac@gamil.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(6)
	public void updateRoleTest() {
		int affected = userRepository.updateRole(2L, Role.ADMINISTRATOR);
		
		assertThat(affected).isEqualTo(1);
	}
}
