package com.example.ps17284_chautandat_asm_duan.Model;

public class PhieuMuon {
    Integer ma;
    String nguoiMuon, sachMuon, ngay;

    public PhieuMuon() {
    }

    public PhieuMuon(Integer ma, String nguoiMuon, String sachMuon, String ngay) {
        this.ma = ma;
        this.nguoiMuon = nguoiMuon;
        this.sachMuon = sachMuon;
        this.ngay = ngay;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getNguoiMuon() {
        return nguoiMuon;
    }

    public void setNguoiMuon(String nguoiMuon) {
        this.nguoiMuon = nguoiMuon;
    }

    public String getSachMuon() {
        return sachMuon;
    }

    public void setSachMuon(String sachMuon) {
        this.sachMuon = sachMuon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
