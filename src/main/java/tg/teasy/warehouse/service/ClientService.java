package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Client;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.ClientRepository;
import tg.teasy.warehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client) {
        boolean existsByName = clientRepository.existsByName(client.getName());
        if (existsByName) return new Result("bunday client bor", false, null);
        clientRepository.save(client);
        return new Result("Saqlandi client", true, null);
    }

    public Result getClientById(Integer clientID) {
        Optional<Client> optionalClient = clientRepository.findById(clientID);
        if (!optionalClient.isPresent()) return new Result("note found", false, new Client());
        Client client = optionalClient.get();
        return new Result("OK", true, client);
    }

    public Result editClientById(Integer clientID, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(clientID);
        if (!optionalClient.isPresent()) return new Result("Bunday client yoq", false, null);
        Client clientToEdit = optionalClient.get();
        clientToEdit.setName(client.getName());
        clientRepository.save(clientToEdit);
        return new Result("Ozgartirildi client", true, clientToEdit.getId());
    }

    public Result deleteClientById(Integer clientID) {
        Optional<Client> optionalClient = clientRepository.findById(clientID);
        if (!optionalClient.isPresent()) return new Result("Bunday client yoq", false, null);
        clientRepository.deleteById(clientID);
        return new Result("Ochirildi client", true, null);
    }


}
