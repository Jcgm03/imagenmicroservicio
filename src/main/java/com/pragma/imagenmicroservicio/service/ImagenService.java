package com.pragma.imagenmicroservicio.service;

import com.pragma.imagenmicroservicio.entity.ImagenEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImagenService {

    public ImagenEntity guardImageMongo (int personaId, MultipartFile file) throws IOException;
    public List<ImagenEntity> obtenerImageMongo (int id);
    public Object elimiarImageMongo(String id);
    public List<ImagenEntity> listarImageMongo ();
    public Optional<ImagenEntity> updateImageMongo(String id,int personaId ,MultipartFile file) throws IOException;
}
