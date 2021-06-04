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

import com.train.model.ModelTrain;
import com.train.repository.RepositoryTrain;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceTrainTest {

	@Autowired
	private RepositoryTrain trainRepository;
	
	@Test 
	@DisplayName("Test for Train findByNumber")
	public void findByNumberTrain() {
		
		String code = "1378";
		Optional<ModelTrain> testTrain = trainRepository.findByNumber(code);
		
		assertNotNull(testTrain);
	}
	
	@Test
	@DisplayName("Test for Train getTrainById")
	public void findByTrain() {
		
		String id = "07";
		Optional<ModelTrain> testTrain = trainRepository.findById(id);
		
		assertNotNull(testTrain);
	}
}
