package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
