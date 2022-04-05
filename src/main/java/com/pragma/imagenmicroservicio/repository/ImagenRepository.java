package com.pragma.imagenmicroservicio.repository;

import com.pragma.imagenmicroservicio.entity.ImagenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImagenRepository extends MongoRepository <ImagenEntity,String> {

    @Query("{id: '?0'}")
    ImagenEntity findIdById(String id);

    @Query("{personaId: ?0}")
    List<ImagenEntity> findAllByPersonaId(int personaId);
}
