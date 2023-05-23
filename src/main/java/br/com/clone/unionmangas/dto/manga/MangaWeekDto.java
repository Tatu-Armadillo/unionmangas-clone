package br.com.clone.unionmangas.dto.manga;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.clone.unionmangas.dto.chapter.ChapterGetDto;
import br.com.clone.unionmangas.model.Manga;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MangaWeekDto {

    private Long idManga;
    private String mainTitle;
    private String linkImage;
    private LocalDate lastUpdate;
    private Set<ChapterGetDto> chapters;

    public MangaWeekDto() { }

    public MangaWeekDto(Manga manga) {
        this.idManga = manga.getIdManga();
        this.mainTitle = manga.getMainTitle();
        this.linkImage = manga.getLinkImage();
        this.lastUpdate = manga.getLastUpdate();
        this.chapters = this.chapersToChapterDtos(manga);
    }

    private Set<ChapterGetDto> chapersToChapterDtos(Manga manga) {
        Set<ChapterGetDto> chapterDtos = new HashSet<>();
        manga.getChapters().forEach(c -> chapterDtos.add(new ChapterGetDto(c)));
        return chapterDtos;
    }

    public Long getIdManga() {
        return idManga;
    }

    public void setIdManga(Long idManga) {
        this.idManga = idManga;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<ChapterGetDto> getChapters() {
        return chapters;
    }

    public void setChapters(Set<ChapterGetDto> chapters) {
        this.chapters = chapters;
    }

}
