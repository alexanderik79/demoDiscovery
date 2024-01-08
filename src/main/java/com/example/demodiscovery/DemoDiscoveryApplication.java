package com.example.demodiscovery;

import com.example.demodiscovery.domain.Client;
import com.example.demodiscovery.repository.ClientRepository;
import com.example.demodiscovery.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SpringBootApplication
@EnableEurekaServer
@RestController
@Slf4j
public class DemoDiscoveryApplication {

    private final ClientService clientService;

    public DemoDiscoveryApplication(ClientService clientService) {
        this.clientService = clientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoDiscoveryApplication.class, args);
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) {
        log.info("\u001B[34m"+"Server: Received message - {}"+"\u001B[0m", message);
    }

    @PostMapping("/receive-client")
    public ResponseEntity<String> receiveClient(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email
    ) {
        Client client = new Client();
        client.setName(name);
        client.setPhone(phone);
        client.setEmail(email);
        clientService.saveClient(client);
        log.info("\u001B[34m"+"Server: Received and saved client - name = {}"+"\u001B[0m", client.getName());
        return new ResponseEntity<>("Client received and saved", HttpStatus.OK);
    }

    @GetMapping("/get-client-by-id")
    public ResponseEntity<String> getClientByName(@RequestParam Long id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            log.info("\u001B[34m"+"Server: Find client - name = {}"+"\u001B[0m", client.getName());
            return new ResponseEntity<>("Name: " + client.getName() + ", Phone: " + client.getPhone() + ", Email: " + client.getEmail(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client with id '" + id + "' not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-phone")
    public ResponseEntity<String> updatePhoneById(@RequestParam Long id, @RequestParam String newPhone) {

        Client client = clientService.getClientById(id);
        if(client != null){
            client.setPhone(newPhone);
            clientService.saveClient(client);
            log.info("\u001B[34m"+"Server: Updated phone of client - name = {}"+"\u001B[0m", client.getName());
            return new ResponseEntity<>("Phone updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client with id '" + id + "' not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-client")
    public ResponseEntity<String> deleteClientById(@RequestParam Long id) {
        Client clientToDelete = clientService.getClientById(id);
        if (clientToDelete !=null) {
            clientService.removeClient(clientToDelete.getId());
            log.info("\u001B[34m"+"Server: Client was deleted - name = {}"+"\u001B[0m", clientToDelete.getName());
            return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client with id '" + id + "' not found", HttpStatus.NOT_FOUND);
        }
    }
}
