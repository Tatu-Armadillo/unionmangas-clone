package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

import jakarta.persistence.*;

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
    private String titleChapter;

    @Column(name = "pages_quantity")
    private Integer pagesQuantity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonIgnore
    @OneToMany(mappedBy = "chapter")
    private List<PagesChapter> pagesChapters;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manga", foreignKey = @ForeignKey(name = "fk_chapters_mangas"))
    private Manga manga;

    public Chapter() { }

    public Chapter(Integer volume, Integer numberChapter, String titleChapter, Integer pagesQuantity) {
        this.volume = volume;
        this.numberChapter = numberChapter;
        this.titleChapter = titleChapter;
        this.pagesQuantity = pagesQuantity;
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

    public String getTitleChapter() {
        return titleChapter;
    }

    public void setTitleChapter(String titleChapter) {
        this.titleChapter = titleChapter;
    }

    public Integer getPagesQuantity() {
        return pagesQuantity;
    }

    public void setPagesQuantity(Integer pagesQuantity) {
        this.pagesQuantity = pagesQuantity;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public List<PagesChapter> getPagesChapters() {
        return pagesChapters;
    }

    public void setPagesChapters(List<PagesChapter> pagesChapters) {
        this.pagesChapters = pagesChapters;
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
