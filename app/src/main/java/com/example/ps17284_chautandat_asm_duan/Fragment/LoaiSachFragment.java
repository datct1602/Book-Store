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

import com.example.ps17284_chautandat_asm_duan.Adapter.LoaiSachAdapter;
import com.example.ps17284_chautandat_asm_duan.Dao.LoaiSachDao;
import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {

    View view;
    RecyclerView rcvLoaiSach;
    LoaiSachDao dao;
    LoaiSachAdapter adapter;
    ArrayList<LoaiSach> list = new ArrayList<>();
    Button themLoai;
    FloatingActionButton btnaddLoai;
    EditText edtAddLoai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        rcvLoaiSach = view.findViewById(R.id.rcvLoaiSach);
        btnaddLoai = view.findViewById(R.id.btnAddLoaiSach);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvLoaiSach.setLayoutManager(manager);
        dao = new LoaiSachDao(getContext());
        list = dao.getAll();
        adapter = new LoaiSachAdapter(list, getContext());
        rcvLoaiSach.setAdapter(adapter);


        btnaddLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.add_loai, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //AddDuLieu
                themLoai = view.findViewById(R.id.themLoai);
                edtAddLoai = view.findViewById(R.id.edtAddLoai);
                themLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenLoai = edtAddLoai.getText().toString();
                        if (dao.insert(tenLoai)) {
                            Toast.makeText(getContext(), "Bạn đã thêm thành công : " + tenLoai, Toast.LENGTH_SHORT).show();
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