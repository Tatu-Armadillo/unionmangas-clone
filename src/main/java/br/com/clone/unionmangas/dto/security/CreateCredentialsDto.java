package br.com.clone.unionmangas.dto.security;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateCredentialsDto {
    private String userName;
    private String password;
    private boolean isScan = false;

    public CreateCredentialsDto() { }

    public CreateCredentialsDto(String userName, String password, boolean isScan) {
        this.userName = userName;
        this.password = password;
        this.isScan = isScan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isScan() {
        return isScan;
    }

    public void setScan(boolean isScan) {
        this.isScan = isScan;
    }
}
