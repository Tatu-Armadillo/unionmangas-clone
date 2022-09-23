package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "manga")
public class Manga {

    @Id
    @Column(name = "id_manga")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManga;

    @Column(name = "main_title")
    private String mainTitle;

    @Column(name = "alternatives_title")
    private String alternativeTitle;

    @Column(name = "link_image")
    private String linkImage;

    @Column(name = "blob_image")
    private byte[] blobImage;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "volume_quantity")
    private Integer volumeQuantity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "manga_author", joinColumns = @JoinColumn(name = "manga"), inverseJoinColumns = @JoinColumn(name = "author"))
    private Set<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manga_genre ", joinColumns = @JoinColumn(name = "manga"), inverseJoinColumns = @JoinColumn(name = "genre"))
    private Set<Genre> genres;

    @JsonIgnore
    @OneToMany(mappedBy = "manga")
    private Set<Chapter> chapters;

    public Long getIdManga() {
        return idManga;
    }

    public void setIdManga(Long idManga) {
        this.idManga = idManga;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getAlternativeTitle() {
        return alternativeTitle;
    }

    public void setAlternativeTitle(String alternativeTitle) {
        this.alternativeTitle = alternativeTitle;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public byte[] getBlobImage() {
        return blobImage;
    }

    public void setBlobImage(byte[] blobImage) {
        this.blobImage = blobImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVolumeQuantity() {
        return volumeQuantity;
    }

    public void setVolumeQuantity(Integer volumeQuantity) {
        this.volumeQuantity = volumeQuantity;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

}
