package com.aws.albums.exception;

public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException(Long id) {
        super("Could not find song " + id);
    }
}
