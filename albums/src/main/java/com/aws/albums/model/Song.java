package com.aws.albums.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "album_object", schema = "aws_s3")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @JsonProperty("id")
    @Column(name = "id")
    private @Id @GeneratedValue Long id;
    @JsonProperty("songName")
    @Column(name = "song_name")
    private String songName;
    @JsonProperty("createdAt")
    @Column(name = "created_at")
    private Timestamp createdAt;
    @JsonProperty("isDeleted")
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty("album_id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JoinColumn(foreignKey = @ForeignKey(name = "album_id"), name = "album_id", referencedColumnName = "id", nullable = false)
    private Album album;

    public Song(String songName, Timestamp createdAt, boolean isDeleted, Album album) {
        this.songName = songName;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.album = album;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.songName, this.createdAt, this.isDeleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Song))
            return false;
        Song song = (Song) o;
        return Objects.equals(this.id, song.id)
                && Objects.equals(this.songName, song.songName)
                && Objects.equals(this.createdAt, song.createdAt)
                && Objects.equals(this.isDeleted, song.isDeleted);
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + this.id + '\'' + ", songName='" + this.songName
                + '\'' + ", createdAt='" + this.createdAt + '\'' + ", isDeleted='" + this.isDeleted + '\'' + '}';
    }
}
