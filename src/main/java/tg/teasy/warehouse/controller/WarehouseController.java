package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.CurrencyService;
import tg.teasy.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;


    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        Result result = warehouseService.addWarehouse(warehouse);
        return result;
    }

    @GetMapping("/warehouseID")
    public Result getWarehouseById(@PathVariable Integer warehouseID) {
        Result result = warehouseService.getWarehouseById(warehouseID);
        return result;
    }

    @PutMapping("/warehouseID")
    public Result editWarehouse(@PathVariable Integer warehouseID, @RequestBody Warehouse warehouse) {
        Result result = warehouseService.editWarehouseById(warehouseID, warehouse);
        return result;
    }

    @DeleteMapping("/warehouseID")
    public Result deleteWarehouse(@PathVariable Integer warehouseID) {
        Result result = warehouseService.deleteWarehouseById(warehouseID);
        return result;
    }


}
