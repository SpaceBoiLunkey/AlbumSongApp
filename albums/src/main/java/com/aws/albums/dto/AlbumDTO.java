package com.aws.albums.dto;

import com.aws.albums.model.Album;
import com.aws.albums.model.Song;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class AlbumDTO {
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("band_name")
    private String bandName;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("is_deleted")
    private boolean isDeleted;
    @JsonIgnore
    private List<Song> songs;


    public static Album dtoToMdo(AlbumDTO albumDTO) {
        Album album = new Album(albumDTO.name, albumDTO.bandName, albumDTO.createdAt, albumDTO.isDeleted);
        return album;
    }

    public static AlbumDTO mdoToDto(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setName(album.getAlbumName());
        albumDTO.setBandName(album.getBandName());
        albumDTO.setCreatedAt(album.getCreatedAt());
        albumDTO.setDeleted(album.isDeleted());
        albumDTO.setSongs(album.getSongs());
        return albumDTO;
    }
}