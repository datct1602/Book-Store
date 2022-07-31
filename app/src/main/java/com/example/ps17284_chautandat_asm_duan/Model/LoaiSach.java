package com.example.ps17284_chautandat_asm_duan.Model;

public class LoaiSach {
    Integer maLoai;
    String tenLoai;

    public LoaiSach() {
    }

    public LoaiSach(Integer maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
