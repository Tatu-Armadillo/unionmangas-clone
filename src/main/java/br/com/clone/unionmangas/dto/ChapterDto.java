package br.com.clone.unionmangas.dto;

import br.com.clone.unionmangas.model.Chapter;

public class ChapterDto {

    private Long idChapter;
    private Integer numberChapter;

    public ChapterDto(Chapter chapter) {
        this.idChapter = chapter.getIdChapter();
        this.numberChapter = chapter.getNumberChapter();
    }

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public Integer getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(Integer numberChapter) {
        this.numberChapter = numberChapter;
    }


}
