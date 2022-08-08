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
    private Byte[] blobImage;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "evaluation")
    private String evaluation;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author", foreignKey = @ForeignKey(name = "fk_manga_author_text"))
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manga_genre", joinColumns = @JoinColumn(name = "id_genre"))
    Set<Genre> genres;
    
    @OneToMany(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_chapter")
    Set<Chapter> chapters;

    public Manga() { }

    public Manga(Long idManga, String mainTitle, String alternativeTitle, String linkImage, Byte[] blobImage,
            String description, String status, String evaluation, LocalDate releaseDate, LocalDate lastUpdate,
            Author author, Set<Genre> genres, Set<Chapter> chapters) {
        this.idManga = idManga;
        this.mainTitle = mainTitle;
        this.alternativeTitle = alternativeTitle;
        this.linkImage = linkImage;
        this.blobImage = blobImage;
        this.description = description;
        this.status = status;
        this.evaluation = evaluation;
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

    public Byte[] getBlobImage() {
        return blobImage;
    }

    public void setBlobImage(Byte[] blobImage) {
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

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
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
