package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Output;

@Repository
public interface OutputRepository extends JpaRepository<Output, Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
