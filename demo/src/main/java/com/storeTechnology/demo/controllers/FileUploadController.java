package com.storeTechnology.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.storeTechnology.demo.model.ResponseObject;
import com.storeTechnology.demo.services.IStorageService;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //This controller receive file/image from client
    // Inject Storage Service here 
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            // save file to a folder => use a service 
            String generatedFileName = storageService.storageFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Upload file successfully", generatedFileName)
                // d05c8a367b0941f3a90ee75adc2cf5e4.png
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("OK", exception.getMessage(), "")
            );    
        }
    }

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            // save file to a folder => use a service 
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                   .ok()
                   .contentType(MediaType.IMAGE_JPEG)
                   .body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();  
        }
    }

}