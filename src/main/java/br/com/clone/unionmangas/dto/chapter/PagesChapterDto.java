package br.com.clone.unionmangas.dto.chapter;

import br.com.clone.unionmangas.model.PagesChapter;

public class PagesChapterDto {

    private Integer pageNumber;
    private Byte[] blobPages;
    private String linkPages;
    private Long idChapter;

    public PagesChapterDto(Long idChapter, Integer pageNumber, Byte[] blobPages, String linkPages) {
        this.idChapter = idChapter;
        this.pageNumber = pageNumber;
        this.blobPages = blobPages;
        this.linkPages = linkPages;
    }

    public static PagesChapterDto of(PagesChapter entity) {
        return new PagesChapterDto(
                entity.getChapter().getIdChapter(),
                entity.getPageNumber(),
                entity.getBlobPages(),
                entity.getLinkPages());
    }

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Byte[] getBlobPages() {
        return blobPages;
    }

    public void setBlobPages(Byte[] blobPages) {
        this.blobPages = blobPages;
    }

    public String getLinkPages() {
        return linkPages;
    }

    public void setLinkPages(String linkPages) {
        this.linkPages = linkPages;
    }

}
