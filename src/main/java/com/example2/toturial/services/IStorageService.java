package com.example2.toturial.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    String storeFile(MultipartFile file);
    Stream<Path> loadAll(); // load all file inside a folder
    public byte[] readFileContent(String fileName);  // request to Server --> response byte[] --> can view image on browers . or reponse link image
    public void deleteAllFiles();
}
