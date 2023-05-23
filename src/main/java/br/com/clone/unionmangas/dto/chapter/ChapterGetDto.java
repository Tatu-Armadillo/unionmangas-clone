package br.com.clone.unionmangas.dto.chapter;

import java.time.LocalDate;

import br.com.clone.unionmangas.model.Chapter;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChapterGetDto {

    private Long idChapter;
    private Integer numberChapter;
    private String titleChapter;
    private LocalDate releaseDate;

    public ChapterGetDto() { }

    public ChapterGetDto(Chapter chapter) {
        this.idChapter = chapter.getIdChapter();
        this.numberChapter = chapter.getNumberChapter();
        this.titleChapter = chapter.getTitleChapter();
        this.releaseDate = chapter.getReleaseDate();
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

    public String getTitleChapter() {
        return titleChapter;
    }

    public void setTitleChapter(String titleChapter) {
        this.titleChapter = titleChapter;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}
