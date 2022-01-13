package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Product;

@Repository
public interface ProductRepositiry extends JpaRepository<Product,Integer> {

   boolean existsByNameAndCategoryId(String name, Integer category_id);


}
