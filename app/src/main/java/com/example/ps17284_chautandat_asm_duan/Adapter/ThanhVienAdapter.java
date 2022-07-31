package com.example.ps17284_chautandat_asm_duan.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps17284_chautandat_asm_duan.Dao.LoaiSachDao;
import com.example.ps17284_chautandat_asm_duan.Dao.ThanhVienDao;
import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.Model.ThanhVien;
import com.example.ps17284_chautandat_asm_duan.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    ArrayList<ThanhVien> list;
    Context context;
    ThanhVienDao dao;
    EditText edtUpdateTen, edtUpdateEmail, edtUpdateSdt, edtUpdatedob;
    Button updateTv;

    public ThanhVienAdapter(ArrayList<ThanhVien> list, Context context) {
        this.context = context;
        this.list = list;
        dao = new ThanhVienDao(context);
    }


    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        ThanhVienAdapter.ThanhVienViewHolder holder = new ThanhVienAdapter.ThanhVienViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = list.get(position);
        holder.tvTen.setText(list.get(position).getTen());
        holder.tvEmail.setText(list.get(position).getEmail());
        holder.tvSDT.setText(list.get(position).getSdt());
        holder.tvDOB.setText(list.get(position).getDob());
        holder.imgUpdateThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdate(thanhVien);
            }
        });
        holder.deleteThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.delete(thanhVien.getMaTv())){
                    Toast.makeText(context, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao.getAll());
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvEmail, tvSDT, tvDOB;
        ImageView deleteThanhVien, imgUpdateThanhVien;

        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            tvDOB = itemView.findViewById(R.id.tvDOB);
            imgUpdateThanhVien = itemView.findViewById(R.id.imgEditTv);
            deleteThanhVien = itemView.findViewById(R.id.imgdeleteTv);
        }
    }

    private void openDialogUpdate(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_thanhvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        edtUpdateTen = view.findViewById( R.id.edtUpdateTen );
        edtUpdateEmail = view.findViewById( R.id.edtUpdateEmail );
        edtUpdateSdt = view.findViewById( R.id.edtUpdateSdt );
        edtUpdatedob = view.findViewById( R.id.edtUpdatedob );
        updateTv = view.findViewById(R.id.updateTv);
        updateTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVien.setTen( edtUpdateTen.getText().toString() );
                thanhVien.setEmail( edtUpdateEmail.getText().toString() );
                thanhVien.setSdt( edtUpdateSdt.getText().toString() );
                thanhVien.setDob( edtUpdatedob.getText().toString() );
                if (dao.update( thanhVien ) ){
                    Toast.makeText(context, "Bạn đã sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(dao.getAll());
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } );

    }
}
