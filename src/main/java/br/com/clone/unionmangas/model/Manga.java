package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author", foreignKey = @ForeignKey(name = "fk_manga_author"))
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manga_genre", joinColumns = @JoinColumn(name = "manga"), inverseJoinColumns = @JoinColumn(name = "genre"))
    Set<Genre> genres;

    @OneToMany(mappedBy = "manga")
    Set<Chapter> chapters;

    public Manga() {
    }

    public Manga(String mainTitle, String alternativeTitle, String linkImage, byte[] blobImage,
            String description, String status, Double rating, Integer volumeQuantity,
            LocalDate releaseDate, LocalDate lastUpdate, Author author, Set<Genre> genres) {
        this.mainTitle = mainTitle;
        this.alternativeTitle = alternativeTitle;
        this.linkImage = linkImage;
        this.blobImage = blobImage;
        this.description = description;
        this.status = status;
        this.rating = rating;
        this.volumeQuantity = volumeQuantity;
        this.releaseDate = releaseDate;
        this.lastUpdate = lastUpdate;
        this.author = author;
        this.genres = genres;
    }

    public Manga(Long idManga, String mainTitle, String alternativeTitle, String linkImage, byte[] blobImage,
            String description, String status, Double rating, Integer volumeQuantity, 
            LocalDate releaseDate, LocalDate lastUpdate, Author author, Set<Genre> genres, Set<Chapter> chapters) {
        this.idManga = idManga;
        this.mainTitle = mainTitle;
        this.alternativeTitle = alternativeTitle;
        this.linkImage = linkImage;
        this.blobImage = blobImage;
        this.description = description;
        this.status = status;
        this.rating = rating;
        this.volumeQuantity = volumeQuantity;
        this.releaseDate = releaseDate;
        this.lastUpdate = lastUpdate;
        this.author = author;
        this.genres = genres;
        this.chapters = chapters;
    }

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
