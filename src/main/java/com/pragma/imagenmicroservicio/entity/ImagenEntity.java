package com.pragma.imagenmicroservicio.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "imagendb")
public class ImagenEntity {

    @Id
    private String id;
    private int personaId;
    private Binary image;

    public ImagenEntity(int personaId) {
        this.personaId = personaId;
    }
    public ImagenEntity() {
    }
}
