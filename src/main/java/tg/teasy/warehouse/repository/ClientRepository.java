package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Client;
import tg.teasy.warehouse.entity.Currency;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByName(String name);
}
