package tg.teasy.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tg.teasy.warehouse.entity.Attachment_Content;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<Attachment_Content, Integer> {
    Optional<Attachment_Content> findByAttachmentId(Integer attachment_id);

}
