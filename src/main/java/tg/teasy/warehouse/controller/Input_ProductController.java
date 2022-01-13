package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.payload.InputDTO;
import tg.teasy.warehouse.payload.Input_ProductDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.Input_ProductService;

@RestController
@RequestMapping("/input_product")
public class Input_ProductController {
    //Input_Product
    @Autowired
    Input_ProductService input_productService;

    @PostMapping
    public Result addInput_Product(@RequestBody Input_ProductDTO inputDTO) {
        Result result = input_productService.addInput_Product(inputDTO);
        return result;
    }

    @GetMapping("/input_prodID")
    public Result getInput_ProductById(@PathVariable Integer input_prodID) {
        Result result = input_productService.getInput_ProductById(input_prodID);
        return result;
    }

    @PutMapping("/input_prodID")
    public Result editInput_ProductById(@PathVariable Integer input_prodID, @RequestBody Input_ProductDTO input_productDTO) {
        Result result = input_productService.editInput_ProductById(input_prodID, input_productDTO);
        return result;
    }

    @DeleteMapping("/input_prodID")
    public Result deleteInput_ProductById(@PathVariable Integer input_prodID) {
        Result result = input_productService.deleteInput_ProductById(input_prodID);
        return result;
    }


}
