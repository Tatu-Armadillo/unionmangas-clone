package br.com.clone.unionmangas.dto.security;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TokenDto {

    private String userName;
    private String token;

    public TokenDto() {
    }

    public TokenDto(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
