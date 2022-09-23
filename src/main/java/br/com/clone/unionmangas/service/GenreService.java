package br.com.clone.unionmangas.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Genre;
import br.com.clone.unionmangas.repository.GenreRepository;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findByName(String name) {
        var respose = this.genreRepository.findByName(name);
        if (respose == null) {
            throw new NegocioException("Genre Not Found");
        }
        return respose;
    }

    public Page<Genre> findAllByName(Pageable pageable, String name) {
        var respose = this.genreRepository.findAllByName(pageable, name);
        return respose;
    }

    public Set<Genre> checkGenres(Set<Genre> genres) {
        if (genres.isEmpty()) {
            throw new NegocioException("Unselected Genres");
        }
        Set<Genre> response = new HashSet<>();
        genres.forEach(g -> {
            var genre = this.findByName(g.getName());
            response.add(genre);
        });
        return response;
    }

}
