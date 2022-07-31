package com.example.ps17284_chautandat_asm_duan.Model;

public class Sach {

    Integer ma;
    String ten, tacgia, gia;

    public Sach() {
    }

    public Sach(Integer ma, String ten, String tacgia, String gia) {
        this.ma = ma;
        this.ten = ten;
        this.tacgia = tacgia;
        this.gia = gia;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
