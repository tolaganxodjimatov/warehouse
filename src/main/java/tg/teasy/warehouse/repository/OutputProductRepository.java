package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.OutputProduct;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {

}
