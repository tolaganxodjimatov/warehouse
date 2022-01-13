package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Output;
import tg.teasy.warehouse.entity.OutputProduct;
import tg.teasy.warehouse.entity.Product;
import tg.teasy.warehouse.payload.OutputProductDTO;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.OutputProductRepository;
import tg.teasy.warehouse.repository.OutputRepository;
import tg.teasy.warehouse.repository.ProductRepositiry;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepositiry productRepositiry;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDTO outputProductDTO) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductDTO.getId());
        if (optionalOutputProduct.isPresent()) return new Result("optionalOutput - mavjud", false, null);

        Optional<Product> optionalProduct = productRepositiry.findById(outputProductDTO.getProduct_id());
        if (!optionalProduct.isPresent()) return new Result("Product - mavjud emas", false, null);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutput_id());
        if (!optionalOutput.isPresent()) return new Result("Output - mavjud emas", false, null);

        OutputProduct outputProduct = new OutputProduct(null, optionalProduct.get(), outputProductDTO.getAmount(), outputProductDTO.getPrice(), optionalOutput.get());
        OutputProduct saved = outputProductRepository.save(outputProduct);

        return new Result("Output - Saved OK", true, saved.getId());
    }

    @GetMapping("/outputProductID")
    public Result getOutputProductById(Integer outputProductID) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductID);
        if (!optionalOutputProduct.isPresent()) return new Result("OutputProduct - mavjud emas", false, null);
        return new Result("OK", true, optionalOutputProduct.get());
    }

    @PutMapping("/outputProductID")
    public Result editOutputProductById(Integer outputProductID, OutputProductDTO outputProductDTO) {

        return new Result();
    }

    @DeleteMapping("/outputProductID")
    public Result deleteOutputProductById(Integer outputProductID) {
        Optional<OutputProduct> byId = outputProductRepository.findById(outputProductID);
        if (!byId.isPresent()) return new Result("OutputProduct - mavjud emas", false, null);
        outputProductRepository.deleteById(outputProductID);
        return new Result("OutputProduct - OK, o'chdi", true, byId.get());
    }


}
