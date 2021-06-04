package com.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.book.model.ModelTicket;

@Repository
public interface RepositoryTicket extends MongoRepository<ModelTicket, String>{

	public Optional<ModelTicket> findByTicketId(String ticketId);

    public Optional<ModelTicket> deleteByTicketId(String ticketId);

    public Optional<ModelTicket> findTicketByPNR(String PNR);

    public Optional<ModelTicket> getTicketByPNR(String PNR);

    public List<ModelTicket> findTicketByTripScheduleId(String tripScheduleId);

	
}
