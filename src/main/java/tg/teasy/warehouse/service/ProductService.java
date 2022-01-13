package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Attachment;
import tg.teasy.warehouse.entity.Category;
import tg.teasy.warehouse.entity.Measurement;
import tg.teasy.warehouse.entity.Product;
import tg.teasy.warehouse.payload.ProductDto;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.AttachmentRepository;
import tg.teasy.warehouse.repository.CategoryRepository;
import tg.teasy.warehouse.repository.MeasurementRepository;
import tg.teasy.warehouse.repository.ProductRepositiry;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepositiry productRepositiry;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProductService(ProductDto productDto) {
        boolean existsByNameAndCategoryId = productRepositiry.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId) {
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud", false, null);
        }
        //kategoriya tekshiramiz
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bunday kategoriya mavjud emas", false, null);

        //Photo tekshiramiz
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("bunday olchov birligi mavjud emas", false, null);

        //mesurment tekshiramiz
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) return new Result("Bunday olchov birligi mavjud emas", false, null);


        //Saqlashni boshlimiz
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(UUID.randomUUID().toString());// *** qoyish kk shu joyini metpd ili nimadur
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        Product savedProduct = productRepositiry.save(product);
        return new Result("Maxsulot saqlandi", true, savedProduct.getId());

    }

    public Result getProductByIDService(Integer productId) {
        Optional<Product> optionalProduct = productRepositiry.findById(productId);
        if (!optionalProduct.isPresent()) return new Result("note found",false,new Product());
        return new Result("OK", true,optionalProduct.get());
    }

    public Result editProductByIDService(Integer productId, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepositiry.findById(productId);
        if (!optionalProduct.isPresent()) return new Result("Bunday mahsulot mavjud emas", false, productId);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Kategoriya mavjud emas", false, null);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) return new Result("bunday maxsulot rasmi mavjud emas", false, null);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) return new Result("Bunday olchov birligi mavjud emas", false, null);

        Product productToEdit = optionalProduct.get();
        productToEdit.setCategory(optionalCategory.get());
        productToEdit.setPhoto(optionalAttachment.get());
        productToEdit.setCode(UUID.randomUUID().toString());
        productToEdit.setMeasurement(optionalMeasurement.get());
        Product savedProduct = productRepositiry.save(productToEdit);
        return new Result("Saqlandi", true, savedProduct.getId());
    }

    public Result deleteProductByIdService(Integer productId) {
        Optional<Product> optionalProduct = productRepositiry.findById(productId);
        if (!optionalProduct.isPresent()) return new Result("Bunday maxsulot topilmadi",false,null);
        productRepositiry.deleteById(productId);
        return new Result("Maxsulot o'chirildi",true,null);


    }

}
