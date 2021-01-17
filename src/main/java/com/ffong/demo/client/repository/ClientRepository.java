package com.ffong.demo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffong.demo.client.model.Client;;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
// TODO
}
