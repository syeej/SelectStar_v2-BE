// package com.threestar.selectstar.domain.service;
//
// import java.util.Optional;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import com.threestar.selectstar.repository.UserRepository;
//
// @Service
// public class UserService {
//
// 	@Autowired
// 	private UserRepository userRepository;
//
// 	public boolean login(String email, String password) {
// 		Optional<User> userOptional = userRepository.findByEmail(email);
// 		if (userOptional.isPresent()) {
// 			User user = userOptional.get();
// 			return user.getPassword().equals(password);
// 		}
// 		return false;
// 	}
// }
//
