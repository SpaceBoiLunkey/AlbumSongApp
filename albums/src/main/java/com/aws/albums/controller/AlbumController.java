package com.aws.albums.controller;


import com.aws.albums.dto.AlbumDTO;
import com.aws.albums.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AlbumController {

    @Autowired
    private final AlbumService albumService;


    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public CollectionModel<AlbumDTO> getAllS3Albums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        return CollectionModel.of(albums, linkTo(methodOn(AlbumController.class).getAllS3Albums()).withSelfRel());
    }

    @GetMapping("/albums/{id}")
    public ResponseEntity<AlbumDTO> getS3AlbumById(@PathVariable Long id) {
        AlbumDTO albumDTO = albumService.getAlbumById(id);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @PostMapping("/albums")
    @ResponseBody
    public ResponseEntity<?> newS3Album(@RequestBody AlbumDTO albumDTO) {
        long id = albumService.newS3Album(albumDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/albums/{id}")
    @ResponseBody
    public ResponseEntity<?> updateS3Album(@PathVariable Long id, @RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.updateAlbum(id, albumDTO), HttpStatus.OK);
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity<?> deleteAlbumById(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.noContent().build();
    }
}
