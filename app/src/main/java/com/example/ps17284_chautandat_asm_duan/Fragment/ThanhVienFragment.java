package com.example.ps17284_chautandat_asm_duan.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps17284_chautandat_asm_duan.Adapter.SachAdapter;
import com.example.ps17284_chautandat_asm_duan.Adapter.ThanhVienAdapter;
import com.example.ps17284_chautandat_asm_duan.Dao.SachDao;
import com.example.ps17284_chautandat_asm_duan.Dao.ThanhVienDao;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.Model.ThanhVien;
import com.example.ps17284_chautandat_asm_duan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {

    View view;
    RecyclerView rcvTv;
    ArrayList<ThanhVien> list = new ArrayList<>();
    ThanhVienDao dao;
    ThanhVienAdapter adapter;
    Button  themTv;
    FloatingActionButton btnAddThanhVien;
    EditText edtAddTenTv, edtAddEmail, edtAddSdt, edtAddDob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rcvTv = view.findViewById(R.id.rcvThanhVien);
        btnAddThanhVien = view.findViewById(R.id.btnAddTv);



        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvTv.setLayoutManager(manager);
        dao = new ThanhVienDao(getContext());
        list = dao.getAll();
        adapter = new ThanhVienAdapter(list, getContext());
        rcvTv.setAdapter(adapter);


        btnAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.add_thanhvien, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //AddDuLieu
                edtAddTenTv = view.findViewById(R.id.edtAddTenTv);
                edtAddEmail = view.findViewById(R.id.edtAddEmail);
                edtAddSdt = view.findViewById(R.id.edtAddSdt);
                edtAddDob = view.findViewById(R.id.edtAddDob);
                themTv = view.findViewById(R.id.themTv);
                themTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtAddTenTv.getText().toString();
                        String email = edtAddEmail.getText().toString();
                        String sdt = edtAddSdt.getText().toString();
                        String dob = edtAddDob.getText().toString();
                        if (dao.insert(ten,email,sdt, dob)) {
                            Toast.makeText(getContext(), "Bạn đã thêm thành công : " + ten, Toast.LENGTH_SHORT).show();
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