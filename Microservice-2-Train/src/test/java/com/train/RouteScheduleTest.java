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

import com.train.model.ModelTripSchedule;
import com.train.repository.RepositoryTripSchedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RouteScheduleTest {

	@Autowired
	RepositoryTripSchedule routeScheduleRepo;
	
	@Test
	@DisplayName("Test Route Schedule for GetRouteSchedule")
	public void getIdTest() {
		
		String id="01";
		
		Optional<ModelTripSchedule> testRoute = routeScheduleRepo.findById(id);
		
		assertNotNull(testRoute);
	}
	
	@Test
	@DisplayName("Test Route Schedule from Route")
	public void getRoute() {
		
		String id="01";
		ModelTripSchedule testRoute = routeScheduleRepo.findByTripId(id);
		
		assertNotNull(testRoute);
	}
	
}

