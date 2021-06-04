package com.train;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.train.model.ModelTrip;
import com.train.repository.RepositoryTrip;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceRouteTest {

	@Autowired
	private RepositoryTrip routeRepo;
	
	@Test
	@DisplayName("Test for Route TripID")
	public void findbyIdTest() {
		
		String id = "01";
		
		Optional<ModelTrip> testRoute = routeRepo.findById(id);
		
		assertNotNull(testRoute);
	}
	
}
