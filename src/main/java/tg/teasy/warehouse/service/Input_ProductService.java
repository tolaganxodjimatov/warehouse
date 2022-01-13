package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Input;
import tg.teasy.warehouse.entity.Input_Product;
import tg.teasy.warehouse.entity.Product;
import tg.teasy.warehouse.payload.Input_ProductDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.InputRepository;
import tg.teasy.warehouse.repository.Input_ProductRepository;
import tg.teasy.warehouse.repository.ProductRepositiry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Optional;

@Service
public class Input_ProductService {
    @Autowired
    Input_ProductRepository input_productRepository;
    @Autowired
    ProductRepositiry productRepositiry;
    @Autowired
    InputRepository inputRepository;

    public Result addInput_Product(Input_ProductDTO input_productDTO) {
        Optional<Product> optionalProduct = productRepositiry.findById(input_productDTO.getProduct_id());
        if (!optionalProduct.isPresent()) return new Result("product yoq", false, null);

        Optional<Input> optionalInput = inputRepository.findById(input_productDTO.getInput_id());
        if (!optionalInput.isPresent()) return new Result("Input yoq", false, null);

        Input_Product inputProduct = new Input_Product();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(input_productDTO.getAmount());
        inputProduct.setPrice(input_productDTO.getPrice());
        inputProduct.setExpireDate(input_productDTO.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        Input_Product saved = input_productRepository.save(inputProduct);
        return new Result("OK", true, saved);

    }


    public Result getInput_ProductById(Integer input_prodID) {
        Optional<Input_Product> optional = input_productRepository.findById(input_prodID);
        if (!optional.isPresent()) return new Result("Bunday Input_Prod mavjud emas", false, null);
        Input_Product input_product = optional.get();
        return new Result("OK", true, input_product);


    }


    public Result editInput_ProductById(Integer input_prodID, Input_ProductDTO input_productDTO) {

        return null;
    }


    public Result deleteInput_ProductById(Integer input_prodID) {
        Optional<Input_Product> optionalInputProduct = input_productRepository.findById(input_prodID);
        if (!optionalInputProduct.isPresent()) return new Result("Bunday Input_Prod mavjud emas", false, null);
        input_productRepository.deleteById(input_prodID);
        return new Result("OK, deleted Input_Prod", true, input_prodID);
    }
}
