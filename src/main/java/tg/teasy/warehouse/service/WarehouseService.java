package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.CurrencyRepository;
import tg.teasy.warehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse) {
        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
        if (existsByName) return new Result("bunday Warehouse bor", false, null);
        warehouseRepository.save(warehouse);
        return new Result("Saqlandi Warehouse", true, null);
    }

    public Result getWarehouseById(Integer warehouseID) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseID);
        if (!optionalWarehouse.isPresent()) return new Result("Warehouse note found",false,new Currency());
        Warehouse warehouse = optionalWarehouse.get();
        return new Result("OK",true, warehouse);
    }

    public Result editWarehouseById(Integer warehouseID, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseID);
        if (!optionalWarehouse.isPresent()) return new Result("Bunday Warehouse yoq", false, null);
        Warehouse warehouseToEdit = optionalWarehouse.get();
        warehouseToEdit.setName(warehouse.getName());
        warehouseRepository.save(warehouseToEdit);
        return new Result("Ozgartirildi Warehouse", true, warehouseToEdit.getId());
    }

    public Result deleteWarehouseById(Integer warehouseID) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseID);
        if (!optionalWarehouse.isPresent()) return new Result("Bunday Warehouse yoq", false, null);
        warehouseRepository.deleteById(warehouseID);
        return new Result("Ochirildi Warehouse", true, null);
    }


}
