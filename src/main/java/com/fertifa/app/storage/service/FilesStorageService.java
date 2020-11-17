package com.fertifa.app.storage.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
   void init();

   void save(MultipartFile file);

   Resource load(String filename);

   void deleteAll();

   Stream<Path> loadAll();
}