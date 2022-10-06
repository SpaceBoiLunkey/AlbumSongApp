package com.aws.albums.controller;


import com.aws.albums.dto.SongDTO;
import com.aws.albums.service.AlbumService;
import com.aws.albums.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SongController {

    @Autowired
    private final SongService songService;
    private final AlbumService albumService;

    SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping("/songs")
    public CollectionModel<SongDTO> getAllS3Songs() {
        List<SongDTO> songs = songService.getAllSongs();
        return CollectionModel.of(songs, linkTo(methodOn(SongController.class).getAllS3Songs()).withSelfRel());
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongDTO> getS3SongById(@PathVariable Long id) {
        SongDTO songDTO = songService.getSongById(id);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/albums/{albumId}/songs")
    public List<SongDTO> getAllSongsByAlbumId(@PathVariable(value = "albumId") Long albumId) {
        return songService.getSongsByAlbumId(albumId);
    }

    @PostMapping("/songs")
    @ResponseBody
    public ResponseEntity<?> newS3Song(@RequestBody SongDTO songDTO) {
        long id = songService.newS3Song(songDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/songs/{id}")
    @ResponseBody
    public ResponseEntity<?> updateS3Song(@PathVariable Long id, @RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<?> deleteSongById(@PathVariable Long id) {
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }
}
