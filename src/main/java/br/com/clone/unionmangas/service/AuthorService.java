package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findByName(final String name) {
        var reponse = this.authorRepository.findByName(name);
        if (reponse == null) {
            return new Author();
            // throw new NegocioException("Author not found");
        }
        return reponse;
    }

    public Author findById(final Long idAuthor) {
        var reponse = this.authorRepository.findById(idAuthor)
                .orElseThrow(() -> new NegocioException("Author not found"));
        return reponse;
    }

    public Author create(final Author author) {
        var reponse = this.authorRepository.save(author);
        return reponse;
    }

}
