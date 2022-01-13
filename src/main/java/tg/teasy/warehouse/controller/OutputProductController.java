package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.payload.OutputDTO;
import tg.teasy.warehouse.payload.OutputProductDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;


    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDTO outputProductDTO) {
        Result result = outputProductService.addOutputProduct(outputProductDTO);
        return result;
    }

    @GetMapping("/outputProductID")
    public Result getOutputProductById(@PathVariable Integer outputProductID) {
        Result result = outputProductService.getOutputProductById(outputProductID);
        return result;
    }

    @PutMapping("/outputProductID")
    public Result editOutputProductById(@PathVariable Integer outputProductID, @RequestBody OutputProductDTO outputProductDTO) {
        Result result = outputProductService.editOutputProductById(outputProductID, outputProductDTO);
        return result;
    }

    @DeleteMapping("/outputProductID")
    public Result deleteOutputProductById(@PathVariable Integer outputProductID) {
        Result result = outputProductService.deleteOutputProductById(outputProductID);
        return result;
    }


}
