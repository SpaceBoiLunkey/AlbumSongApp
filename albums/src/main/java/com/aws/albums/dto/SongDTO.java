package com.aws.albums.dto;

import com.aws.albums.model.Album;
import com.aws.albums.model.Song;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SongDTO {
    private Long id;
//    @JsonProperty("album_id")
//    private Long albumId;
    @JsonProperty("song_name")
    private String songName;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("is_deleted")
    private boolean isDeleted;
    @JsonProperty("album_id")
    private Album album;

    public static Song dtoToMdo(SongDTO songDTO) {
        Song song = new Song(songDTO.songName, songDTO.createdAt, songDTO.isDeleted, songDTO.album);
        return song;
    }

    public static SongDTO mdoToDto(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
//        songDTO.setAlbumId(song.getAlbumId());
        songDTO.setSongName(song.getSongName());
        songDTO.setCreatedAt(song.getCreatedAt());
        songDTO.setDeleted(song.isDeleted());
        return songDTO;
    }
}
