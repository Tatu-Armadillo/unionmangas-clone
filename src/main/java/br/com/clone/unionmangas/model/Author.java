package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthor;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "manga_author", joinColumns = @JoinColumn(name = "author"), inverseJoinColumns = @JoinColumn(name = "manga"))
    private Set<Manga> mangas;

    public Author() { }

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

    public Set<Manga> getManga() {
        return mangas;
    }

    public void setManga(Set<Manga> manga) {
        this.mangas = manga;
    }

}
