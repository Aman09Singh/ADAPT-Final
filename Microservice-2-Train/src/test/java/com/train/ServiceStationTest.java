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

import com.train.model.ModelStation;
import com.train.repository.RepositoryStation;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceStationTest {

	@Autowired
	private RepositoryStation stationRepository;
	
	
	@Test
	@DisplayName("Test for Station getStationByCode")
	public void findByCodeStation() {
		
		String code = "2457";
		Optional<ModelStation> testStation = stationRepository.findByCode(code);
		
		assertNotNull(testStation);
		
	}
	
	@Test
	@DisplayName("Test for Station getStationById")
	public void findByIdStation() {
		
		String id = "02";
		
		Optional<ModelStation> testStation = stationRepository.findById(id);
		
		assertNotNull(testStation);
	}
}
