package com.example.apiFiles.controller;

import com.example.apiFiles.entity.FileEntity;
import com.example.apiFiles.response.ResponseFile;
import com.example.apiFiles.response.ResponseMessage;
import com.example.apiFiles.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/fileManager")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        if(file.getContentType() == null){
            throw new IOException();
        }
        fileService.store(file);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Archivo subido exitosamente"));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
        FileEntity fileEntity = fileService.getFile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, fileEntity.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .body(fileEntity.getData());

    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = fileService.getAllFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}