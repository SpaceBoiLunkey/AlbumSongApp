package com.aws.albums.exception;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(Long id) {
        super("Could not find album " + id);
    }
}