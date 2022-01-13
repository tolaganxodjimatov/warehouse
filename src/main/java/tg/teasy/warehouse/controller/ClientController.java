package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Client;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.ClientService;
import tg.teasy.warehouse.service.CurrencyService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    //Client

    @PostMapping
    public Result addClient(@RequestBody Client client) {
        Result result = clientService.addClient(client);
        return result;
    }

    @GetMapping("/clientID")
    public Result getClientById(@PathVariable Integer clientID) {
        Result result = clientService.getClientById(clientID);
        return result;
    }

    @PutMapping("/clientID")
    public Result editClientById(@PathVariable Integer clientID, @RequestBody Client client) {
        Result result = clientService.editClientById(clientID, client);
        return result;
    }

    @DeleteMapping("/clientID")
    public Result deleteClientById(@PathVariable Integer clientID) {
        Result result = clientService.deleteClientById(clientID);
        return result;
    }


}
