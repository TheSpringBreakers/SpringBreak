package com.codingdojo.com.SpringBreakers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.com.SpringBreakers.models.Ticket;

@Repository
public interface TicketRepo extends CrudRepository<Ticket,Long>{
	List<Ticket> findAll();
}
