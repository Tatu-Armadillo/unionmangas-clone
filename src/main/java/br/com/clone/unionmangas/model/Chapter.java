package br.com.clone.unionmangas.model;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @Column(name = "id_chapter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChapter;

    @Column(name = "number_chapter")
    private Long numberChapter;

    @Column(name = "number_pages")
    private Long numberPages;

    @Column(name = "link_chapter")
    private Byte[] linkChapter;

    @Column(name = "blob_chapter")
    private Byte[] blobChapter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manga", foreignKey = @ForeignKey(name = "fk_chapter_manga"))
    private Manga manga;

    public Chapter() { }

    public Chapter(Long idChapter, Long numberChapter, Long numberPages, Byte[] linkChapter, Byte[] blobChapter,
            Manga manga) {
        this.idChapter = idChapter;
        this.numberChapter = numberChapter;
        this.numberPages = numberPages;
        this.linkChapter = linkChapter;
        this.blobChapter = blobChapter;
        this.manga = manga;
    }

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public Long getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(Long numberChapter) {
        this.numberChapter = numberChapter;
    }

    public Long getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(Long numberPages) {
        this.numberPages = numberPages;
    }

    public Byte[] getLinkChapter() {
        return linkChapter;
    }

    public void setLinkChapter(Byte[] linkChapter) {
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

}
