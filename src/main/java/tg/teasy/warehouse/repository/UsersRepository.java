package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Users;
import tg.teasy.warehouse.entity.Warehouse;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
