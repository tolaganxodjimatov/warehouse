package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
