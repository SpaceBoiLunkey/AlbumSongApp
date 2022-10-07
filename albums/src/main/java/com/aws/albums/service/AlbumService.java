package com.aws.albums.service;

import com.aws.albums.dto.AlbumDTO;
import com.aws.albums.exception.AlbumNotFoundException;
import com.aws.albums.model.Album;
import com.aws.albums.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    public long newS3Album(AlbumDTO albumDTO) {
        Album album = AlbumDTO.dtoToMdo(albumDTO);
        Album savedAlbum = albumRepository.save(album);
        return savedAlbum.getId();
    }

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        albums.forEach(album -> {
            AlbumDTO dto = AlbumDTO.mdoToDto(album);
            albumDTOS.add(dto);
        });
        return albumDTOS;
    }

    public AlbumDTO getAlbumById(long id) {
        Optional<Album> result = albumRepository.findById(id);
        if (result.isPresent()) {
            Album album = result.get();
            AlbumDTO dto = AlbumDTO.mdoToDto(album);
            return dto;
        } else {
            throw new AlbumNotFoundException(id);
        }
    }

    public void deleteAlbumById(long id) {
        albumRepository.deleteById(id);
    }

    public AlbumDTO updateAlbum(long id, AlbumDTO albumDTO) {
        AlbumDTO albumDTONew = null;
        if (albumRepository.findById(id).isPresent()) {
            Album album = albumRepository.findById(id).get();
            album.setAlbumName(albumDTO.getName());
            album.setBandName(albumDTO.getBandName());
            albumDTONew = AlbumDTO.mdoToDto(album);
            return albumDTONew;
        }
        return albumDTONew;
    }
}
