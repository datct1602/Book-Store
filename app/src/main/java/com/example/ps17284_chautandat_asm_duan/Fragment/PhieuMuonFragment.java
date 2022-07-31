package com.example.ps17284_chautandat_asm_duan.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ps17284_chautandat_asm_duan.Adapter.PhieuMuonAdapter;
import com.example.ps17284_chautandat_asm_duan.Adapter.SachAdapter;
import com.example.ps17284_chautandat_asm_duan.Dao.PhieuMuonDao;
import com.example.ps17284_chautandat_asm_duan.Dao.SachDao;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PhieuMuonFragment extends Fragment {

    View view;
    RecyclerView rcvPhieuMuon;
    ArrayList<PhieuMuon> list = new ArrayList<>();
    PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    Button  themPhieu;
    FloatingActionButton btnaddPhieu;
    EditText edtAddNguoiMuon, edtAddSachMuon, edtAddNgay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        rcvPhieuMuon = view.findViewById(R.id.rcvPhieuMuon);
        btnaddPhieu = view.findViewById(R.id.btnAddPhieuMuon);



        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvPhieuMuon.setLayoutManager(manager);
        dao = new PhieuMuonDao(getContext());
        list = dao.getAll();
        adapter = new PhieuMuonAdapter(list, getContext());
        rcvPhieuMuon.setAdapter(adapter);

        btnaddPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.add_phieumuon, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //AddDuLieu
                edtAddNguoiMuon = view.findViewById(R.id.edtAddNguoiMuon);
                edtAddSachMuon = view.findViewById(R.id.edtAddSachMuon);
                edtAddNgay = view.findViewById(R.id.edtAddNgay);
                themPhieu = view.findViewById(R.id.themPhieu);
                themPhieu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nguoiMuon = edtAddNguoiMuon.getText().toString();
                        String sachMuon = edtAddSachMuon.getText().toString();
                        String ngay = edtAddNgay.getText().toString();
                        if (dao.insert(nguoiMuon,sachMuon,ngay)) {
                            Toast.makeText(getContext(), "Bạn đã thêm thành công : " + nguoiMuon, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            list.clear();
                            list.addAll(dao.getAll());
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } );



        return view;
    }
}