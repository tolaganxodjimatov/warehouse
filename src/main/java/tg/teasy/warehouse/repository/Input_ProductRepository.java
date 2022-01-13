package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Input_Product;

@Repository
public interface Input_ProductRepository extends JpaRepository<Input_Product,Integer> {
}
