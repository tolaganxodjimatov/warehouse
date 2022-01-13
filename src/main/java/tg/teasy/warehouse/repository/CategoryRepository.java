package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
