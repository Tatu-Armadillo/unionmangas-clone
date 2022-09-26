package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @Column(name = "id_chapter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChapter;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "number_chapter")
    private Integer numberChapter;

    @Column(name = "title_chapter")
    private Integer titleChapter;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "link_pages")
    private String linkPages;

    @Column(name = "blob_pages")
    private Byte[] blobPages;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manga", foreignKey = @ForeignKey(name = "fk_chapters_mangas"))
    private Manga manga;

    public Chapter() { }

    public Chapter(Long idChapter, Integer volume, Integer numberChapter, Integer pages, String linkPages,
            Byte[] blobPages) {
        this.idChapter = idChapter;
        this.volume = volume;
        this.numberChapter = numberChapter;
        this.pages = pages;
        this.linkPages = linkPages;
        this.blobPages = blobPages;
    }

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(Integer numberChapter) {
        this.numberChapter = numberChapter;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLinkPages() {
        return linkPages;
    }

    public void setLinkPages(String linkPages) {
        this.linkPages = linkPages;
    }

    public Byte[] getBlobPages() {
        return blobPages;
    }

    public void setBlobPages(Byte[] blobPages) {
        this.blobPages = blobPages;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Chapter that = (Chapter) o;
        return Objects.equals(this.volume, that.volume)
                && Objects.equals(this.numberChapter, that.numberChapter);
    }

}
