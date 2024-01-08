package com.storeTechnology.demo.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Stream;
import java.nio.file.Path;

public interface IStorageService {
    public String storageFile(MultipartFile file);
    public Stream<Path> loadFile(); // load all file in floder
    public byte[] readFileContent(String fileName);
    public void deleteAllFiles();
    
}
