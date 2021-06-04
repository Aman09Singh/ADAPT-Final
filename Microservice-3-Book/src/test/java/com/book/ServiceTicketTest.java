package com.book;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.book.model.ModelTicket;
import com.book.repository.RepositoryTicket;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceTicketTest {

	@Autowired
	private RepositoryTicket ticketRepo;
	
	@Test
	@DisplayName("Test for finding ticket by id")
	public void getByIdTest() {
		
		String id = "60845ded521a70456c81aae4";
		
		Optional<ModelTicket> testTicket = ticketRepo.findByTicketId(id);
		
		assertNotNull(testTicket);
	}
	
	@Test
	@DisplayName("Test for getting PNR in Ticket Booking")
	public void getPNRTest() {
		String pnr = "123520210415FAC-29";
		Optional<ModelTicket> testPnr = ticketRepo.getTicketByPNR(pnr);
		
		assertNotNull(testPnr);
	}
	
	@Test 
	@DisplayName("Test for finding by pnr")
	public void findByPNR() {
		String pnr = "123520210415FAC-29";
		Optional<ModelTicket> testPnr = ticketRepo.findTicketByPNR(pnr);
		
		assertNotNull(testPnr);
	}
}
