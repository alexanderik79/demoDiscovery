package com.example.demodiscovery.service;

import com.example.demodiscovery.domain.Client;
import com.example.demodiscovery.repository.ClientRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient (Client client){
        return clientRepository.save(client);
    }
    @Transactional
    public void removeClient (Long clientId){
        clientRepository.removeClientById(clientId);
    }

    public Client getClientById (Long id){
        return clientRepository.findClientById(id);
    }


}