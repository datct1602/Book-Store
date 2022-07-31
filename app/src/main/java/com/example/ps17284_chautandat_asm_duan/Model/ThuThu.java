package com.example.ps17284_chautandat_asm_duan.Model;

public class ThuThu {

    String username, password;

    public ThuThu(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ThuThu() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
