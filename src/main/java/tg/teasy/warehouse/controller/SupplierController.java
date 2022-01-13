package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Supplier;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        Result result = supplierService.addSupplier(supplier);
        return result;
    }

    @GetMapping("/{supplierId}")
    public Result getSupplierById(@PathVariable Integer supplierId) {
        Result result = supplierService.getSupplierById(supplierId);
        return result;
    }

    @PutMapping("/{supplierId}")
    public Result editSupplierById(@PathVariable Integer supplierId, @RequestBody Supplier supplier) {
        Result result = supplierService.editSupplierById(supplierId, supplier);
        return result;
    }

    @DeleteMapping("/{supplierId}")
    public Result deleteSupplierById(@PathVariable Integer supplierId) {
        Result result = supplierService.deleteSupplierById(supplierId);
        return result;
    }
}
