package com.user;

import static org.junit.Assert.assertNotNull;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.user.model.ModelUser;
import com.user.repository.RepositoryUser;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Microservice1UserApplicationTests {

	
	@Autowired
	private RepositoryUser userRepository;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	@DisplayName("Test findUserByEmail REST API Endpoint")
	public void findByEmail() {
		
	String email = "user@gmail.com";
	Optional<ModelUser> foundUser = userRepository.findByEmail(email);
	
	assertNotNull(foundUser);
		
	}

}
