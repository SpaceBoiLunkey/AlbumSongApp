package com.aws.albums.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "album_bucket", schema = "aws_s3")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @JsonProperty("id")
    @Column(name = "id")
    private @Id @GeneratedValue Long id;
    @JsonProperty("albumName")
    @Column(name = "album_name")
    private String albumName;
    @JsonProperty("bandName")
    @Column(name = "band_name")
    private String bandName;
    @JsonProperty("createdAt")
    @Column(name = "created_at")
    private Timestamp createdAt;
    @JsonProperty("isDeleted")
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Song> songs;

    public Album(String albumName, String bandName, Timestamp createdAt, boolean isDeleted) {
        this.albumName = albumName;
        this.bandName = bandName;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.albumName, this.bandName, this.createdAt, this.isDeleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Album))
            return false;
        Album album = (Album) o;
        return Objects.equals(this.id, album.id)
                && Objects.equals(this.albumName, album.albumName)
                && Objects.equals(this.bandName, album.bandName)
                && Objects.equals(this.createdAt, album.createdAt)
                && Objects.equals(this.isDeleted, album.isDeleted);
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + this.id + ", albumName='" + this.albumName + '\'' + ", bandName='" + this.bandName
                + '\'' + ", createdAt='" + this.createdAt + '\'' + ", isDeleted='" + this.isDeleted + '\'' + '}';
    }
}