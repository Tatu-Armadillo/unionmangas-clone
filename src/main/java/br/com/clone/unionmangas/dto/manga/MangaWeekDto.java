package br.com.clone.unionmangas.dto.manga;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.clone.unionmangas.dto.ChapterDto;
import br.com.clone.unionmangas.model.Manga;

public class MangaWeekDto {

    private Long idManga;
    private String mainTitle;
    private String linkImage;
    private LocalDate lastUpdate;
    private Set<ChapterDto> chapters;

    public MangaWeekDto(Manga manga) {
        this.idManga = manga.getIdManga();
        this.mainTitle = manga.getMainTitle();
        this.linkImage = manga.getLinkImage();
        this.lastUpdate = manga.getLastUpdate();
        this.chapters = this.chapersToChapterDtos(manga);
    }

    private Set<ChapterDto> chapersToChapterDtos(Manga manga) {
        Set<ChapterDto> chapterDtos = new HashSet<>();
        manga.getChapters().forEach(c -> chapterDtos.add(new ChapterDto(c)));
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

    public Set<ChapterDto> getChapters() {
        return chapters;
    }

    public void setChapters(Set<ChapterDto> chapters) {
        this.chapters = chapters;
    }

}
