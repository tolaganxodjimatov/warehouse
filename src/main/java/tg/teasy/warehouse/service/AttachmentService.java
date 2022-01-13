package tg.teasy.warehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tg.teasy.warehouse.entity.Attachment;
import tg.teasy.warehouse.entity.Attachment_Content;
import tg.teasy.warehouse.payload.CategoryDto;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.AttachmentContentRepository;
import tg.teasy.warehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        Attachment_Content attachmentContent = new Attachment_Content();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(saveAttachment);
        Attachment_Content savedAttachm = attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi", true, savedAttachm.getId());
    }

    @SneakyThrows
    public Result editAttachmentByIDService(Integer attachmentId, HttpServletResponse response) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent()) return new Result("Yoq bunday attach", false, null);
        Attachment attachment = optionalAttachment.get();

        Optional<Attachment_Content> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        if (!optionalAttachmentContent.isPresent())
            return new Result("Yoq bunday Id bilan biriktirilgan attach fayl", false, null);
        Attachment_Content attachmentContent = optionalAttachmentContent.get();
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());

        return new Result("OK fayl ketgan bolishi kk", true, null);
    }

    public Result deleteAttachmentByIDService(Integer attachmentId) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent()) return new Result("Yoq bunday attach", false, null);

        Optional<Attachment_Content> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        if (!optionalAttachmentContent.isPresent())
            return new Result("Yoq bunday Id bilan biriktirilgan attach fayl", false, null);

        Attachment_Content attachmentContent = optionalAttachmentContent.get();
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(attachmentId);
        return new Result("ochirildi", true, null);

    }


}
