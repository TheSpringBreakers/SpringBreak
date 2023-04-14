package com.codingdojo.com.SpringBreakers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.com.SpringBreakers.models.Ticket;
import com.codingdojo.com.SpringBreakers.models.User;
import com.codingdojo.com.SpringBreakers.repositories.TicketRepo;

@Service
public class TicketServ {
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	public List<Ticket> getAll() {
		return ticketRepo.findAll();
	}
	
	public Ticket getOne(Long id) {
		Optional<Ticket> optionalTicket = ticketRepo.findById(id);
		if(optionalTicket.isPresent()) return optionalTicket.get();
		else return null;
	}
	public Ticket saveOne(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	 public Ticket update(Ticket ticket) {
		 return ticketRepo.save(ticket);
	 }
	 public void deleteTicket(Long id) {
		 ticketRepo.deleteById(id);
	 }
	 public void likeTicket(Long ticketId, Long userId) {
		 User user = userServ.getUser(userId);
		 
		 Ticket ticket=this.getOne(ticketId);
		 
		 ticket.getLikedUsers().add(user);
		 
		 ticketRepo.save(ticket);
	 }
	 
	 public void unlikeTicket(long ticketId, Long userId) {
		 
		 User user =userServ.getUser(userId);
		 
		 Ticket ticket=this.getOne(ticketId);
		 
		 ticket.getLikedUsers().remove(user);
		 
		 ticketRepo.save(ticket);
	 }
}
