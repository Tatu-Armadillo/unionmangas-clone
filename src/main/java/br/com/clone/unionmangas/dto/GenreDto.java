package br.com.clone.unionmangas.dto;

import br.com.clone.unionmangas.model.Genre;

public class GenreDto {
    
    private Long idGenre;
    private String name;

    public GenreDto(Genre genre) {
        this.idGenre = genre.getIdGenre();
        this.name = genre.getName();
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

}
