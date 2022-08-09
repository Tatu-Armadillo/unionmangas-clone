package br.com.clone.unionmangas.service;

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

}
