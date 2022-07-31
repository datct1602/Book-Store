package com.example.ps17284_chautandat_asm_duan.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps17284_chautandat_asm_duan.Adapter.SachAdapter;
import com.example.ps17284_chautandat_asm_duan.Dao.SachDao;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SachFragment extends Fragment {

    View view;
    RecyclerView rcvSach;
    ArrayList<Sach> list = new ArrayList<>();
    SachDao dao;
    SachAdapter adapter;
    Button themSach;
    FloatingActionButton btnaddSach;
    EditText edtAddTenSach, edtAddTacGia, edtAddGia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sach, container, false);
        rcvSach = view.findViewById(R.id.rcvSach);
        btnaddSach = view.findViewById(R.id.btnAddSach);



        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(manager);
        dao = new SachDao(getContext());
        list = dao.getAll();
        adapter = new SachAdapter(list, getContext());
        rcvSach.setAdapter(adapter);


        btnaddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.add_sach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //AddDuLieu
                edtAddTenSach = view.findViewById(R.id.edtAddTenSach);
                edtAddTacGia = view.findViewById(R.id.edtAddTacGia);
                edtAddGia = view.findViewById(R.id.edtAddGia);
                themSach = view.findViewById(R.id.themSach);
                themSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sach = edtAddTenSach.getText().toString();
                        String tacgia = edtAddTacGia.getText().toString();
                        String gia = edtAddGia.getText().toString();
                        if (dao.insert(sach,tacgia,gia)) {
                            Toast.makeText(getContext(), "Bạn đã thêm thành công : " + sach, Toast.LENGTH_SHORT).show();
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