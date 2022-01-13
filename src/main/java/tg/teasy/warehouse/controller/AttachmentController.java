package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        Result result = attachmentService.uploadFile(request);
        return result;
    }

    @PutMapping("/{attachmentId}")
    public Result editAttachmentByIDContr(@PathVariable Integer attachmentId, HttpServletResponse response) {
        Result result = attachmentService.editAttachmentByIDService(attachmentId, response);
        return result;
    }

    @DeleteMapping("/{attachmentId}")
    public Result deleteAttachmentByID(@PathVariable Integer attachmentId){
        Result result = attachmentService.deleteAttachmentByIDService(attachmentId);
        return result;
    }

}
