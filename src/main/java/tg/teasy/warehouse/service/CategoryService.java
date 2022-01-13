package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Category;
import tg.teasy.warehouse.payload.CategoryDto;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) return new Result("Bunaqa ota kategorya mavjud emas", true, null);
            category.setParentCategory(optionalParentCategory.get());
        }
        Category savedCategore = categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi", true, savedCategore.getId());
    }

    public Result getCategoryByIDService(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent())  return new Result("note found",false,new Category());
        return new Result("OK",true,optionalCategory.get());
    }

    public Result editCategoryByIDService(Integer categoryId, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) return new Result("Mavjud emas Kategoriya", false, null);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> categoryOptional2 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!categoryOptional2.isPresent()) {
                category.setParentCategory(categoryOptional2.get());
            }
        }

        Category savedCategory = categoryRepository.save(category);
        return new Result("OK", true, savedCategory.getId());

    }

    public Result deleteCategoryByIDService(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) return new Result("Kategoriya mavjud emas!", false, null);
        categoryRepository.deleteById(categoryId);
        return new Result("Kategoriya o'chirildi", true, null);
    }

}
