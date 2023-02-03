package com.example2.toturial.controller;

import com.example2.toturial.models.ResponseObjectDTO;
import com.example2.toturial.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //Inject Storage Service here
    @Autowired
    private IStorageService storageService;

    @PostMapping("")
    public ResponseEntity<ResponseObjectDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        // try catch để nhỡ đâu ko có quyền truy cập File / ko tìm thấy ...
        try {
            //save files to a folder => use a service
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObjectDTO("ok", "upload file successfully", generatedFileName)
            );
            //ca591ffa7f5a485db48c56d6442baca8.jfif => how to open this file in Web Browser ?
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObjectDTO("ok", exception.getMessage(), "")
            );
        }
    }

    //get image's url
    @GetMapping("/files/{fileName:.+}")
    // /files/ca591ffa7f5a485db48c56d6442baca8.jfif
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    //How to load all uploaded files ?
    @GetMapping("")
    public ResponseEntity<ResponseObjectDTO> getUploadedFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        //convert fileName to url(send request "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObjectDTO("ok", "List files successfully", urls));
        }catch (Exception exception) {
            return ResponseEntity.ok(new
                    ResponseObjectDTO("failed", "List files failed", new String[] {}));
        }
    }
}
