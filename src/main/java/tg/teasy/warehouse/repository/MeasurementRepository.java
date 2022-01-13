package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);

}
