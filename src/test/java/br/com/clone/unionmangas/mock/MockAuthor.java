package br.com.clone.unionmangas.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.dto.author.AuthorParamDto;
import br.com.clone.unionmangas.model.Author;

public class MockAuthor {

    public List<Author> mockAuthorList() {
        List<Author> authors = new ArrayList<>();
        for (Long i = 0L; i < 10L; i++) {
            authors.add(this.mockAuthor(i));
        }
        return authors;
    }

    public Author mockAuthor(Long number) {
        Author author = new Author();
        author.setIdAuthor(number.longValue());
        author.setName("name" + number);
        author.setPseudonym("pseudonym" + number);
        author.setBirthdate(LocalDate.now().minusYears(number));
        return author;
    }

    public Author mockAuthor1() {
        Author author = new Author();
        author.setName("name" + 1);
        author.setPseudonym("pseudonym" + 1);
        author.setBirthdate(LocalDate.now().minusYears(1));
        return author;
    }

    public List<AuthorGetDto> mockAuthorGetDtoList() {
        List<AuthorGetDto> authors = new ArrayList<>();
        for (Long i = 0L; i < 10L; i++) {
            authors.add(this.mockAuthorGetDto(i));
        }
        return authors;
    }

    public AuthorGetDto mockAuthorGetDto(Long number) {
        AuthorGetDto author = new AuthorGetDto();
        author.setIdAuthor(number.longValue());
        author.setName("name" + number);
        author.setPseudonym("pseudonym" + number);
        return author;
    }
    
    public AuthorParamDto mockAuthorParamDto(Long number) {
        AuthorParamDto author = new AuthorParamDto();
        author.setName("name" + number);
        author.setPseudonym("pseudonym" + number);
        author.setBirthdate(LocalDate.now().minusYears(number.intValue()));
        return author;
    }

}
