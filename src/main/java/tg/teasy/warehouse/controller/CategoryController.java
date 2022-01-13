package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Category;
import tg.teasy.warehouse.entity.Product;
import tg.teasy.warehouse.payload.CategoryDto;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategoryService(categoryDto);
        return result;
    }

    @GetMapping("/{categoryId}")
    public Result  getCategorytByIDController(@PathVariable Integer categoryId){
        Result result = categoryService.getCategoryByIDService(categoryId);

        return result;
    }

    @PutMapping("/{categoryId}")
    public Result editCategoryByIdController(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategoryByIDService(categoryId, categoryDto);
        return  result;
    }

    @DeleteMapping("/{categoryId}")
    public Result deleteCategoryByIdController(@PathVariable Integer categoryId){
        Result result = categoryService.deleteCategoryByIDService(categoryId);
        return  result;
    }

}
