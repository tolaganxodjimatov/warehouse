package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.InputDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    //Input
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDTO inputDTO) {
        Result result = inputService.addWarehouse(inputDTO);
        return result;
    }

    @GetMapping("/inputID")
    public Result getInputById(@PathVariable Integer inputID) {
        Result result = inputService.getInputById(inputID);
        return result;
    }

    @PutMapping("/inputID")
    public Result editInputById(@PathVariable Integer inputID, @RequestBody InputDTO inputDTO) {
        Result result = inputService.editInputById(inputID, inputDTO);
        return result;
    }

    @DeleteMapping("/inputID")
    public Result deleteInputById(@PathVariable Integer inputID) {
        Result result = inputService.deleteInputById(inputID);
        return result;
    }
}
