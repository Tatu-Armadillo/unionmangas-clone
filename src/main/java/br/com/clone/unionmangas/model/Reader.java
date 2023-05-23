package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @Column(name = "id_reader")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReader;

    @Column(name = "email")
    private String email;

    @Column(name = "quantity_read")
    private Integer quantityRead;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "readers_chapters", joinColumns = @JoinColumn(name = "reader"), inverseJoinColumns = @JoinColumn(name = "chapter"))
    private Set<Chapter> chapters;

    public Reader(String email, Integer quantityRead, LocalDate birthdate, User user) {
        this.email = email;
        this.quantityRead = quantityRead;
        this.birthdate = birthdate;
        this.user = user;
    }

    public Long getIdReader() {
        return idReader;
    }

    public void setIdReader(Long idReader) {
        this.idReader = idReader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getQuantityRead() {
        return quantityRead;
    }

    public void setQuantityRead(Integer quantityRead) {
        this.quantityRead = quantityRead;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

}
