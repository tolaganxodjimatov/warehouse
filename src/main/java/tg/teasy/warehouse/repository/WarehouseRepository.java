package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);
}
