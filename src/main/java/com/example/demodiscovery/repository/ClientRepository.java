package com.example.demodiscovery.repository;

import com.example.demodiscovery.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);

    Client findClientById(Long clientId);
    @Transactional
    void removeClientById(Long clientId);

}
