package br.com.clone.unionmangas.dto.chapter;

import br.com.clone.unionmangas.model.Chapter;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ChapterParamDto {

    private Integer volume;
    private Integer numberChapter;
    private String titleChapter;
    private Integer pagesQuantity;
    private List<PagesChapterDto> pagesImagens;

    public ChapterParamDto() { }

    public ChapterParamDto(Chapter chapter) {
        this.volume = chapter.getVolume();
        this.numberChapter = chapter.getNumberChapter();
        this.titleChapter = chapter.getTitleChapter();
        this.pagesQuantity = chapter.getPagesQuantity();
        this.pagesImagens = chapter.getPagesChapters().stream().map(PagesChapterDto::of).toList();
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

    public List<PagesChapterDto> getPagesImagens() {
        return pagesImagens;
    }

    public void setPagesImagens(List<PagesChapterDto> pagesImagens) {
        this.pagesImagens = pagesImagens;
    }

}
