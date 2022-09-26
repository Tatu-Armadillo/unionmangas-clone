package br.com.clone.unionmangas.dto;

import br.com.clone.unionmangas.model.Chapter;

public class ChapterDto {

    private Long idChapter;
    private Integer numberVolume;
    private Integer numberChapter;

    public ChapterDto() {
    }

    public ChapterDto(Chapter chapter) {
        this.idChapter = chapter.getIdChapter();
        this.numberVolume = chapter.getVolume();
        this.numberChapter = chapter.getNumberChapter();
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

}
