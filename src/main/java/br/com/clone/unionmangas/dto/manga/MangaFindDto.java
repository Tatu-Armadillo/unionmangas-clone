package br.com.clone.unionmangas.dto.manga;

import java.util.HashSet;
import java.util.Set;

import br.com.clone.unionmangas.dto.GenreDto;
import br.com.clone.unionmangas.model.Manga;

public class MangaFindDto {

    private Long idManga;
    private String mainTitle;
    private String linkImage;
    private Set<GenreDto> genres;

    public MangaFindDto(Manga manga) {
        this.idManga = manga.getIdManga();
        this.mainTitle = manga.getMainTitle();
        this.linkImage = manga.getLinkImage();
        this.genres = this.genreToGenreDto(manga);
    }

    private Set<GenreDto> genreToGenreDto(Manga manga) {
        Set<GenreDto> genres = new HashSet<>();
        manga.getGenres().forEach(c -> genres.add(new GenreDto(c)));
        return genres;
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

    public Set<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDto> genres) {
        this.genres = genres;
    }

}