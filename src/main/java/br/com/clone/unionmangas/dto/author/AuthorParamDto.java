package br.com.clone.unionmangas.dto.author;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import br.com.clone.unionmangas.model.Author;

public class AuthorParamDto extends RepresentationModel<AuthorParamDto> {

    private String name;
    private String pseudonym;
    private LocalDate birthdate;

    public AuthorParamDto() { }

    public AuthorParamDto(String name, String pseudonym, LocalDate birthdate) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.birthdate = birthdate;
    }

    public static AuthorParamDto of(Author author) {
        return new AuthorParamDto(
            author.getName(),
            author.getPseudonym(),
            author.getBirthdate()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
