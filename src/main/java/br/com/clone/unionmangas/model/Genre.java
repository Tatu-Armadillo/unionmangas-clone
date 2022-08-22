package br.com.clone.unionmangas.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.clone.unionmangas.enumerator.AgeGroupEnum;


@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "id_genre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenre;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "age_group")
    @Enumerated(EnumType.STRING)
    private AgeGroupEnum ageGroup;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manga_genre ", joinColumns = @JoinColumn(name = "genre"), inverseJoinColumns = @JoinColumn(name = "manga"))
    private Set<Manga> genres;

    public Genre() { }

    public Genre(String name, AgeGroupEnum ageGroup, String description) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.description = description;
    }

    public Genre(Long idGenre, String name, AgeGroupEnum ageGroup, String description) {
        this.idGenre = idGenre;
        this.name = name;
        this.ageGroup = ageGroup;
        this.description = description;
    }

    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeGroupEnum getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroupEnum ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Manga> getGenres() {
        return genres;
    }

    public void setGenres(Set<Manga> genres) {
        this.genres = genres;
    }

}
