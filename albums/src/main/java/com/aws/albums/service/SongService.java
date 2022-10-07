package com.aws.albums.service;

import com.aws.albums.dto.SongDTO;
import com.aws.albums.exception.SongNotFoundException;
import com.aws.albums.model.Song;
import com.aws.albums.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.aws.albums.dto.SongDTO.mdoToDto;

@Service
@Transactional
public class SongService {
    @Autowired
    SongRepository songRepository;

    public long newS3Song(SongDTO songDTO) {
        Song song = SongDTO.dtoToMdo(songDTO);
        Song savedSong = songRepository.save(song);
        return savedSong.getId();
    }

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        List<SongDTO> songDTOS = new ArrayList<>();
        songs.forEach(song -> {
            SongDTO dto = mdoToDto(song);
            songDTOS.add(dto);
        });
        return songDTOS;
    }

    public SongDTO getSongById(long id) {
        Optional<Song> result = songRepository.findById(id);
        if (result.isPresent()) {
            Song song = result.get();
            SongDTO dto = mdoToDto(song);
            return dto;
        } else {
            throw new SongNotFoundException(id);
        }
    }

    public List<SongDTO> getSongsByAlbumId(long albumId) {
        List<Song> songs = songRepository.findByAlbumId(albumId);
        return songs.stream().map(song -> mdoToDto(song)).collect(Collectors.toList());
    }

    public void deleteSongById(long id) {
        songRepository.deleteById(id);
    }

    public SongDTO updateSong(long id, SongDTO songDTO) {
        SongDTO songDTONew = null;
        if (songRepository.findById(id).isPresent()) {
            Song song = songRepository.findById(id).get();
            song.setSongName(songDTO.getSongName());
            songDTONew = mdoToDto(song);
            return songDTONew;
        }
        return songDTONew;
    }
}
