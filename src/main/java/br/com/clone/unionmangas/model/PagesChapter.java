package br.com.clone.unionmangas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pages_chapters")
public class PagesChapter {

    @Id
    @Column(name = "id_pages_chapter")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagesChapter;

    @Column(name = "page_number")
    private Integer pageNumber;

    @Column(name = "link_pages")
    private String linkPages;

    @Column(name = "blob_pages")
    private Byte[] blobPages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chapter", foreignKey = @ForeignKey(name = "fk_pages_chapters_chapters"))
    private Chapter chapter;

    public PagesChapter() { }

    public PagesChapter(Integer pageNumber, String linkPages, Byte[] blobPages, Chapter chapter) {
        this.pageNumber = pageNumber;
        this.linkPages = linkPages;
        this.blobPages = blobPages;
        this.chapter = chapter;
    }

    public Long getIdPagesChapter() {
        return idPagesChapter;
    }

    public void setIdPagesChapter(Long idPagesChapter) {
        this.idPagesChapter = idPagesChapter;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

}
