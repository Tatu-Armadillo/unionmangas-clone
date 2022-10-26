package br.com.clone.unionmangas.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.clone.unionmangas.enumerator.AgeGroupEnum;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "age_group")
    @Enumerated(EnumType.STRING)
    private AgeGroupEnum ageGroup;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mangas_categories ", joinColumns = @JoinColumn(name = "category"), inverseJoinColumns = @JoinColumn(name = "manga"))
    private Set<Manga> mangas;

    public Category() { }

    public Category(String name, AgeGroupEnum ageGroup, String description) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.description = description;
    }

    public Category(Long idCategory, String name, AgeGroupEnum ageGroup, String description) {
        this.idCategory = idCategory;
        this.name = name;
        this.ageGroup = ageGroup;
        this.description = description;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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

    public Set<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Set<Manga> mangas) {
        this.mangas = mangas;
    }

}
