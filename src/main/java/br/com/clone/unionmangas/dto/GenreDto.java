package br.com.clone.unionmangas.dto;

import java.util.*;
import java.util.stream.Collectors;

import br.com.clone.unionmangas.model.Genre;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenreDto implements Comparable<GenreDto> {

    private Long idGenre;
    private String name;

    public GenreDto() { }

    public GenreDto(Genre genre) {
        this.idGenre = genre.getIdGenre();
        this.name = genre.getName();
    }

    public static List<GenreDto> genreSetToGenreDtoList(Set<Genre> genres) {
        final var genresDto = genres.stream()
                .map(GenreDto::new)
                .collect(Collectors.toList());
        Collections.sort(genresDto);
        return genresDto;
    }

    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(GenreDto o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

}
