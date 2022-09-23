package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @Column(name = "id_chapter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChapter;

    @Column(name = "number_volume")
    private Integer numberVolume;

    @Column(name = "number_chapter")
    private Integer numberChapter;

    @Column(name = "number_pages")
    private Integer numberPages;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "link_chapter")
    private String linkChapter;

    @Column(name = "blob_chapter")
    private Byte[] blobChapter;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manga", foreignKey = @ForeignKey(name = "fk_chapter_manga"))
    private Manga manga;

    public Chapter() { }

    public Chapter(Long idChapter, Integer numberVolume, Integer numberChapter, Integer numberPages, String linkChapter, Byte[] blobChapter) {
        this.idChapter = idChapter;
        this.numberVolume = numberVolume;
        this.numberChapter = numberChapter;
        this.numberPages = numberPages;
        this.linkChapter = linkChapter;
        this.blobChapter = blobChapter;
    }

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public Integer getNumberVolume() {
        return numberVolume;
    }

    public void setNumberVolume(Integer numberVolume) {
        this.numberVolume = numberVolume;
    }

    public Integer getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(Integer numberChapter) {
        this.numberChapter = numberChapter;
    }

    public Integer getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(Integer numberPages) {
        this.numberPages = numberPages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLinkChapter() {
        return linkChapter;
    }

    public void setLinkChapter(String linkChapter) {
        this.linkChapter = linkChapter;
    }

    public Byte[] getBlobChapter() {
        return blobChapter;
    }

    public void setBlobChapter(Byte[] blobChapter) {
        this.blobChapter = blobChapter;
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
        return Objects.equals(this.numberVolume, that.numberVolume)
                && Objects.equals(this.numberChapter, that.numberChapter);
    }

}
