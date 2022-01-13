package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Client;
import tg.teasy.warehouse.payload.OutputDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDTO outputDTO) {
        Result result =outputService.addOutput(outputDTO);
        return result;
    }

    @GetMapping("/outputID")
    public Result getOutputById(@PathVariable Integer outputID) {
        Result result = outputService.getOutputById(outputID);
        return result;
    }

    @PutMapping("/outputID")
    public Result editOutputById(@PathVariable Integer outputID, @RequestBody OutputDTO outputDTO) {
        Result result = outputService.editOutputById(outputID, outputDTO);
        return result;
    }

    @DeleteMapping("/outputID")
    public Result deleteOutputById(@PathVariable Integer outputID) {
        Result result = outputService.deleteOutputById(outputID);
        return result;
    }


}
