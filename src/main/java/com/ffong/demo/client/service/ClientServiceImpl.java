package com.ffong.demo.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffong.demo.client.model.Client;
import com.ffong.demo.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Client> list() {
		List<Client> clients = new ArrayList<Client>();
		clients = clientRepository.findAll();
		return clients;
	}

	@Override
	public Client insert(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client update(Long idClient, Client client) {
		Optional<Client> clientFind = clientRepository.findById(idClient);
		if (clientFind.isPresent()) {
			client.setId(idClient);
			return clientRepository.save(client);
		}
		return null;
	}

	@Override
	public void delete(Long idClient) {
		clientRepository.deleteById(idClient);
	}

}
