package com.hbsmoura.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbsmoura.springcourse.domain.Request;
import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.dto.UserLoginDTO;
import com.hbsmoura.springcourse.dto.UserSaveDTO;
import com.hbsmoura.springcourse.dto.UserUpdateDTO;
import com.hbsmoura.springcourse.dto.UserUpdateRoleDTO;
import com.hbsmoura.springcourse.model.PageModel;
import com.hbsmoura.springcourse.model.PageRequestModel;
import com.hbsmoura.springcourse.service.RequestService;
import com.hbsmoura.springcourse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDTO userDTO) {
		User user = userDTO.turnIntoUser();
		User created = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(
			@PathVariable(name = "id") Long id,
			@RequestBody @Valid UserUpdateDTO userDTO) {
		User user = userDTO.turnIntoUser();
		user.setId(id);
		User updated = userService.save(user);
		return ResponseEntity.ok(updated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> list(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listOnLazyModel(pr);
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginDTO user) {
		User logged = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(logged);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listRequestsById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listByOwnerIdOnLazyModel(id, pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(
			@PathVariable(name = "id") Long id,
			@RequestBody @Valid UserUpdateRoleDTO userDTO){
		User user = new User();
		user.setId(id);
		user.setRole(userDTO.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
	}
}
