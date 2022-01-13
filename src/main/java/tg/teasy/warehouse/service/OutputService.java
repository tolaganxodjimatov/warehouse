package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.*;
import tg.teasy.warehouse.payload.OutputDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.ClientRepository;
import tg.teasy.warehouse.repository.CurrencyRepository;
import tg.teasy.warehouse.repository.OutputRepository;
import tg.teasy.warehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;


    public Result addOutput(OutputDTO outputDTO) {
        boolean isExists = outputRepository.existsByFactureNumber(outputDTO.getFactureNumber());
        if (isExists) return new Result("Bunday faktura mavjud", false, null);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) return new Result("Warehouse - mavjud emas", false, null);

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClient_id());
        if (!optionalClient.isPresent()) return new Result("Client - mavjud emas", false, null);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrency_id());
        if (!optionalCurrency.isPresent()) return new Result("Currency - mavjud emas", false, null);

        Output outputNew = new Output(null, new Timestamp(System.currentTimeMillis()),
                optionalWarehouse.get(),
                optionalClient.get(),
                optionalCurrency.get(),
                outputDTO.getFactureNumber(),
                outputDTO.getCode()
        );

        Output saved = outputRepository.save(outputNew);

        return new Result("OK", true, saved.getId());
    }


    public Result getOutputById(Integer outputID) {
        Optional<Output> optionalOutput = outputRepository.findById(outputID);
        if (!optionalOutput.isPresent()) return new Result("Bunday Output mavjud emas", false, null);
        return new Result("OK", true, optionalOutput.get());
    }


    public Result editOutputById(Integer outputID, OutputDTO outputDTO) {

        Optional<Output> optionalOutput = outputRepository.findById(outputID);
        if (!optionalOutput.isPresent()) return new Result("Output - mavjud emas", false, null);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) return new Result("Warehouse - mavjud emas", false, null);

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClient_id());
        if (!optionalClient.isPresent()) return new Result("Client - mavjud emas", false, null);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrency_id());
        if (!optionalCurrency.isPresent()) return new Result("Currency - mavjud emas", false, null);

        Output outputToEdit = optionalOutput.get();
        outputToEdit.setDate(new Timestamp(System.currentTimeMillis()));
        outputToEdit.setWarehouse(optionalWarehouse.get());
        outputToEdit.setClient(optionalClient.get());
        outputToEdit.setCurrency(optionalCurrency.get());
        outputToEdit.setFactureNumber(outputDTO.getFactureNumber());
        outputToEdit.setCode(outputDTO.getCode());

        Output saved = outputRepository.save(outputToEdit);

        return new Result("OK", true, saved.getId());
    }


    public Result deleteOutputById(Integer outputID) {
        Optional<Output> optionalOutput = outputRepository.findById(outputID);
        if (!optionalOutput.isPresent()) return new Result("Output - mavjud emas", false, null);
        outputRepository.deleteById(outputID);
        return new Result("Output - OK, o'chdi", true, optionalOutput.get());
    }

}
