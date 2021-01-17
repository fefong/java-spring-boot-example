package com.ffong.demo.client.service;

import java.util.List;

import com.ffong.demo.client.model.Client;

public interface ClientService {

	public List<Client> list();
	
	public Client insert(Client client);
	
	public Client update(Long idClient, Client client);
	
	public void delete(Long idClient);
	
}
