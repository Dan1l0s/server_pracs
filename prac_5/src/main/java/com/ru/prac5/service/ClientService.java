package com.ru.prac5.service;

import com.google.gson.Gson;
import com.ru.prac5.model.Client;
import com.ru.prac5.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String createClient(Client client) {
        Optional<Client> existingClient = clientRepository.findById(client.getId());
        if (existingClient.isPresent()) {
            return "error";
        }
        clientRepository.save(client);
        return "created";
    }

    public List<Client> getAllClients() {
        return clientRepository.findAllByOrderByIdAsc();
    }

    public String getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Gson gson = new Gson();
            String json = gson.toJson(client.get());
            return json;
        }
        return "error";
    }

    public String updateClient(int id, Client clientDetail) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.get();
            existingClient.replace(clientDetail);
            clientRepository.save(existingClient);
            return "updated";
        }
        return "error";
    }

    public String deleteClient(int id) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            clientRepository.deleteById(id);
            return "deleted";
        }
        return "error";
    }
}
