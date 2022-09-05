package br.com.clone.unionmangas.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Page<AuthorGetDto> findByName(final Pageable pageable, final String name) {
        var response = this.authorRepository.findByName(pageable, name).map(AuthorGetDto::of);
        return response;
    }
    
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
        author.setAge(this.calcAge(author.getBirthdate()));
        final var response = this.authorRepository.save(author);
        return response;
    }

    private Integer calcAge(LocalDate birthdate) {
        final var currentDate = LocalDate.now();
        final Period period = Period.between(birthdate, currentDate);
        Integer age = period.getYears();
        return age;
    }

}
