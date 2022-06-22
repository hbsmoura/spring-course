package com.hbsmoura.springcourse.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hbsmoura.springcourse.domain.User;
import com.hbsmoura.springcourse.exception.NotFoundException;
import com.hbsmoura.springcourse.model.PageModel;
import com.hbsmoura.springcourse.model.PageRequestModel;
import com.hbsmoura.springcourse.repository.UserRepository;
import com.hbsmoura.springcourse.service.util.HashUtil;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		return userRepository.save(user);
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		return userRepository.save(user);
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There is no user with this Id = " + id));
	}
	
	public List<User> list(){
		return userRepository.findAll();
	}
	
	public PageModel<User> listOnLazyModel(PageRequestModel prm){
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());
		Page<User> page = userRepository.findAll(pageable);
		
		PageModel<User> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
	
	public User login(String email, String password){
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}
	
	public int updateRole(User user) {
		return userRepository.updateRole(user.getId(), user.getRole());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByEmail(username);
		
		if(result.isPresent()) throw new UsernameNotFoundException("There is no user with such email: " + username);
		
		User user = result.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE " + user.getRole().name()));
		
		org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
		return userSpring;
	}
	
}
