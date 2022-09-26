package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthor;

    @Column(name = "name")
    private String name;

    @Column(name = "pseudonym")
    private String pseudonym;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mangas_authors", joinColumns = @JoinColumn(name = "author"), inverseJoinColumns = @JoinColumn(name = "manga"))
    private Set<Manga> mangas;

    public Author() { }

    public Author(String name, String pseudonym, Integer age, LocalDate birthdate) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.age = age;
        this.birthdate = birthdate;
    }
    
    public Author(Long idAuthor, String name, Integer age, LocalDate birthdate) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

}
