package br.com.clone.unionmangas.dto.security;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateCredentialsDto {
    private String email;
    private String fullName;
    private String password;

    public CreateCredentialsDto() { }

    public CreateCredentialsDto(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
