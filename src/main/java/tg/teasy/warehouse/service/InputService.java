package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Input;
import tg.teasy.warehouse.entity.Supplier;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.InputDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.CurrencyRepository;
import tg.teasy.warehouse.repository.InputRepository;
import tg.teasy.warehouse.repository.SupplierRepository;
import tg.teasy.warehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addWarehouse(InputDTO inputDTO) {
        boolean isExists = inputRepository.existsByFactureNumber(inputDTO.getFactureNumber());
        if (isExists) return new Result("Bunday faktura mavjud", false, null);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) return new Result("Bunday Warehouse mavjud emas", false, null);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplier_id());
        if (!optionalSupplier.isPresent()) return new Result("Bunday Supplier mavjud emas", false, null);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrency_id());
        if (!optionalCurrency.isPresent()) return new Result("Bunday Currency mavjud emas", false, null);

        Input input = new Input(null, new Timestamp(System.currentTimeMillis()),
                optionalWarehouse.get(),
                optionalSupplier.get(),
                optionalCurrency.get(),
                inputDTO.getFactureNumber(),
                inputDTO.getCode()
        );

        Input saved = inputRepository.save(input);

        return new Result("OK", true, saved.getId());


    }

    public Result getInputById(Integer inputID) {
        Optional<Input> optionalInput = inputRepository.findById(inputID);
        if (!optionalInput.isPresent()) return new Result("Bunday Input mavjud emas", false, null);
        return new Result("OK", true, optionalInput.get());
    }

    public Result editInputById(Integer inputID, InputDTO inputDTO) {
        Optional<Input> optionalInput = inputRepository.findById(inputID);
        if (!optionalInput.isPresent()) return new Result("Input - mavjud emas", false, null);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) return new Result("Warehouse - mavjud emas", false, null);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplier_id());
        if (!optionalSupplier.isPresent()) return new Result("Supplier - mavjud emas", false, null);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrency_id());
        if (!optionalCurrency.isPresent()) return new Result("Currency - mavjud emas", false, null);

        Input inputToEdit = optionalInput.get();
        inputToEdit.setDate(inputDTO.getDate());
        inputToEdit.setWarehouse(optionalWarehouse.get());
        inputToEdit.setSupplier(optionalSupplier.get());
        inputToEdit.setCurrency(optionalCurrency.get());
        inputToEdit.setFactureNumber(inputDTO.getFactureNumber());
        inputToEdit.setCode(inputDTO.getCode());

        Input saved = inputRepository.save(inputToEdit);
        return new Result("OK", true, saved.getId());
    }

    public Result deleteInputById(Integer inputID) {
        Optional<Input> inputOptional = inputRepository.findById(inputID);
        if (!inputOptional.isPresent()) return new Result("Input - mavjud emas", false, inputID);
        inputRepository.deleteById(inputID);
        return new Result("Input - OK, o'chdi", true, inputID);
    }
}
