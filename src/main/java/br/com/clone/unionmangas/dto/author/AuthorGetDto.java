package br.com.clone.unionmangas.dto.author;

import br.com.clone.unionmangas.model.Author;

public class AuthorGetDto {

    private String name;
    private String pseudonym;
    private Integer age;

    public AuthorGetDto(String name, String pseudonym, Integer age) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.age = age;
    }

    public static AuthorGetDto of(Author author) {
        return new AuthorGetDto(author.getName(), author.getPseudonym(), author.getAge());
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
