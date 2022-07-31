package com.example.ps17284_chautandat_asm_duan.Model;

public class ThanhVien {
    Integer maTv;
    String ten, email, sdt, dob;

    public ThanhVien() {
    }

    public ThanhVien(Integer maTv, String ten, String email, String sdt, String dob) {
        this.maTv = maTv;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.dob = dob;
    }

    public Integer getMaTv() {
        return maTv;
    }

    public void setMaTv(Integer maTv) {
        this.maTv = maTv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
