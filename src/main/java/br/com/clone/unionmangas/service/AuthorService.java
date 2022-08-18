package br.com.clone.unionmangas.service;

import java.time.LocalDate;
import java.time.Period;

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
        var response = this.authorRepository.findByName(name);
        return response;
    }

    public Author findById(final Long idAuthor) {
        var response = this.authorRepository.findById(idAuthor)
                .orElseThrow(() -> new NegocioException("Author not found"));
        return response;
    }

    public Author create(final Author author) {
        var response = this.findByName(author.getName());
        if (response == null) {
            author.setAge(this.calcAge(author.getBirthdate()));
            response = this.authorRepository.save(author);
        }
        return response;
    }

    private Integer calcAge(LocalDate birthdate) {
        final var currentDate = LocalDate.now();
        final Period period = Period.between(birthdate, currentDate);
        Integer age = period.getYears();
        return age;
    }

}
