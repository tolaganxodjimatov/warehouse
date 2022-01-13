package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Product;
import tg.teasy.warehouse.payload.ProductDto;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProductService(productDto);
        return result;
    }

    @GetMapping("/{productId}")
    public Result getProductByIDController(@PathVariable Integer productId) {
        Result result = productService.getProductByIDService(productId);
        return result;
    }

    @PutMapping("/{productId}")
    public Result editProductByID(@PathVariable Integer productId, @RequestBody ProductDto productDto){
        Result result = productService.editProductByIDService(productId, productDto);
        return result;
    }

    @DeleteMapping("/{productId}")
    public Result deleteProductContr(Integer productId){
        Result result = productService.deleteProductByIdService(productId);
        return result;
    }
}
