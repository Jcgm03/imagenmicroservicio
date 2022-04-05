package com.pragma.imagenmicroservicio.controller;

import com.pragma.imagenmicroservicio.entity.ImagenEntity;
import com.pragma.imagenmicroservicio.service.ImagenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/img")
public class ImagenController {
    @Autowired
    private ImagenService imageService;

    @Operation(summary = "Listar imagenes en mongo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagenes listadas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenEntity.class)) })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ImagenEntity> listar(){
        return imageService.listarImageMongo();
    }

    @Operation(summary = "Guardar imagen en mongo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen guardada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Imagen no guardada",
                    content = @Content) })
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ImagenEntity guardarImagen(
            @RequestParam("userId") int personaId,
            @RequestParam("img") MultipartFile file) throws Exception {
        return imageService.guardImageMongo(personaId,file);
    }

    @Operation(summary = "Buscar imagen en mongo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Imagen no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la imagen",
                    content = @Content) })

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ImagenEntity> buscarImagen (@PathVariable int id){
        return imageService.obtenerImageMongo(id);
    }

    @Operation(summary = "Eliminar imagen en mongo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen eliminada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Imagen no se puedo eliminar",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la imagen",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarImagen (@PathVariable String id){
        imageService.elimiarImageMongo(id);
        return ResponseEntity.ok("Registro "+id+" eliminado..");
    }

    @Operation(summary = "Actualizar imagen en mongo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen actualizada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImagenEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Imagen no se puedo actualizar",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la imagen",
                    content = @Content) })

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Optional<ImagenEntity> updateImagenMongo (
            @RequestParam("id") String id,
            @RequestParam("img") MultipartFile file,
            @RequestParam("userId") int userId) throws Exception{
        return imageService.updateImageMongo(id,userId,file);
    }
}
