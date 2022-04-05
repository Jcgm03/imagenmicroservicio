package com.pragma.imagenmicroservicio.service;

import com.pragma.imagenmicroservicio.config.exception.BadRequestException;
import com.pragma.imagenmicroservicio.config.exception.NotFoundException;
import com.pragma.imagenmicroservicio.entity.ImagenEntity;
import com.pragma.imagenmicroservicio.repository.ImagenRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService{

    @Autowired
    private ImagenRepository imagenRepository;

    @Override
    public ImagenEntity guardImageMongo(int personaId, MultipartFile file) throws IOException {
        ImagenEntity imageMongoGuardar = new ImagenEntity(personaId);
        imageMongoGuardar.setImage( new Binary(BsonBinarySubType.BINARY,file.getBytes()));
        imagenRepository.insert(imageMongoGuardar);
        return imageMongoGuardar;
    }

    @Override
    public List<ImagenEntity> obtenerImageMongo(int id) {
        return imagenRepository.findAllByPersonaId(id);
    }

    @Override
    public Object elimiarImageMongo(String id) {
        Optional<ImagenEntity> imageMongoEliminar =  imagenRepository.findById(id);
        if (!imageMongoEliminar.isPresent()) {
            throw new NotFoundException("El id de la imagen no existe");
        }
        imagenRepository.delete(imageMongoEliminar.get());
        return null;
    }

    @Override
    public List<ImagenEntity> listarImageMongo() {
        return imagenRepository.findAll();
    }

    @Override
    public Optional<ImagenEntity> updateImageMongo(String id,int personaId, MultipartFile file) throws IOException {
        Optional<ImagenEntity> imageMongoUpdate = imagenRepository.findById(id);
        if (!imageMongoUpdate.isPresent()){
            throw new NotFoundException("El id '"+id+"' no existe");
        }
        try {
            imageMongoUpdate.get().setImage(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
            imageMongoUpdate.get().setPersonaId(personaId);
        } catch (Exception e){
            throw new BadRequestException("No se puedo actualizar la image");
        }
        imagenRepository.save(imageMongoUpdate.get());
        return null;
    }


}
