package com.example.apiFiles.services;

import com.example.apiFiles.entity.FileEntity;
import com.example.apiFiles.response.ResponseFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface FileService {

    // Permite almacenar o cargar archivos a la base de datos
    FileEntity store(MultipartFile file) throws IOException;

    // Obtener el archivo desde la base de datos
    Optional<FileEntity> getFile (UUID id) throws FileNotFoundException;

    // Permite consultar la lista de archivos cargados a nuestra base de datos
    List<ResponseFile> getAllFiles();
}