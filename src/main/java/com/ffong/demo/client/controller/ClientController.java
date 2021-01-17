package com.ffong.demo.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffong.demo.client.model.Client;
import com.ffong.demo.client.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping()
	public List<Client> list() {
		return clientService.list();
	}

	@PostMapping()
	public Client insert(@RequestBody Client client) {
		return clientService.insert(client);
	}

	@PutMapping("/{idClient}")
	public Client update(@PathVariable Long idClient, @RequestBody Client client) {
		return clientService.update(idClient, client);
	}

	@DeleteMapping("/{idClient}")
	public void delete(@PathVariable Long idClient) {
		clientService.delete(idClient);
	}

}
