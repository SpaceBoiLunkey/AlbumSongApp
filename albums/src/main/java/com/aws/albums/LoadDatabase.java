package com.aws.albums;

import com.aws.albums.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AlbumRepository albumRepository) {

        return args -> {

            albumRepository.findAll().forEach(s3Album -> log.info("Preloaded " + s3Album));

//            s3SongRepository.findAll().forEach(s3Song -> {
//                log.info("Preloaded " + s3Song);
//            });

        };
    }
}
