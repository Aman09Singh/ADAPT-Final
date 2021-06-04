package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.book.model.ModelTicket;
import com.book.repository.RepositoryTicket;


@Service
public class ServiceTicket {

	@Autowired
	RepositoryTicket ticketRepo;
	
		public ModelTicket createTicket(ModelTicket ticket){
	       return ticketRepo.save(ticket);
	    }

	    public Optional<ModelTicket> updateTicket(String ticketId, ModelTicket ticket){
	        Optional<ModelTicket> ticketData = ticketRepo.findByTicketId(ticketId);
	        if(ticketData.isPresent()){
	            return Optional.of(ticketRepo.save(ticket));
	        }
	        return Optional.empty();
	    }

	    public Optional<ModelTicket> deleteTicket(String ticketId){
	    	Optional<ModelTicket> ticket = ticketRepo.deleteByTicketId(ticketId);
	        return ticket;
	    }

	    public Optional<ModelTicket> getTicketByTicketId(String ticketId){
	        Optional<ModelTicket> ticketData = ticketRepo.findByTicketId(ticketId);
	        return ticketData;
	    }

	    public Optional<ModelTicket> getTicketByPNR(String PNR){
	        return  ticketRepo.getTicketByPNR(PNR);
	    }

	    public ModelTicket updateTicketByPNR(String PNR, ModelTicket ticket){
	        Optional<ModelTicket> ticketData = ticketRepo.findTicketByPNR(PNR);
	        if(ticketData.isPresent()){
	        	ticketRepo.save(ticket);
	        }
	        return ticket;
	    }

	    public List<ModelTicket> getTicketsByTripScheduleId(String tripSchedule){
	        return ticketRepo.findTicketByTripScheduleId(tripSchedule);
	    }
	    
	    public Optional<List<ModelTicket>> getAll(){
	    	
	    	return Optional.ofNullable(ticketRepo.findAll());
	    }
	    
}
