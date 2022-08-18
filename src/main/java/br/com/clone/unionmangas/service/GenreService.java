package br.com.clone.unionmangas.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.model.Genre;
import br.com.clone.unionmangas.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre findByName(String name) {
        var respose = this.genreRepository.findByName(name);
        return respose;
    }

    public Set<Genre> checkGenres(Set<Genre> genres) {
        Set<Genre> response = new HashSet<>();
        genres.forEach(g -> {
            var genre = this.findByName(g.getName());
            response.add(genre);
        });
        return response;
    }

}
