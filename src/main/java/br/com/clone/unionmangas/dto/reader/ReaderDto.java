package br.com.clone.unionmangas.dto.reader;

import java.time.LocalDate;

public class ReaderDto {

    private String email;
    private Integer quantityRead;
    private LocalDate birthdate = null;

    public ReaderDto() { }

    public ReaderDto(String email, Integer quantityRead, LocalDate birthdate) {
        this.email = email;
        this.quantityRead = quantityRead;
        this.birthdate = birthdate;
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

}
