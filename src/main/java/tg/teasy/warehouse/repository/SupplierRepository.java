package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
boolean existsByPhoneNumber(String phoneNumber);

}
