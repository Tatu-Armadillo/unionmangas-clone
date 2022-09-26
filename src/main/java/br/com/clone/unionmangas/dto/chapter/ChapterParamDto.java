package br.com.clone.unionmangas.dto.chapter;

import br.com.clone.unionmangas.model.Chapter;

public class ChapterParamDto {

    private Integer volume;
    private Integer numberChapter;
    private String titleChapter;
    private Integer pages;
    private String linkPages;

    public ChapterParamDto() { }

    public ChapterParamDto(Chapter chapter) {
        this.volume = chapter.getVolume();
        this.numberChapter = chapter.getNumberChapter();
        this.titleChapter = chapter.getTitleChapter();
        this.linkPages = chapter.getLinkPages();
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getLinkPages() {
        return linkPages;
    }

    public void setLinkPages(String linkPages) {
        this.linkPages = linkPages;
    }

}
