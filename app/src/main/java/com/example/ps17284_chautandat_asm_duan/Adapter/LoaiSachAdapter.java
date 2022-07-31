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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps17284_chautandat_asm_duan.Dao.LoaiSachDao;
import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {

    ArrayList<LoaiSach> list;
    Context context;
    LoaiSachDao dao;
    Button updateLoai;
    EditText edtUpdateLoai;


    public LoaiSachAdapter(ArrayList<LoaiSach> list, Context context) {
        this.context = context;
        this.list = list;
        dao = new LoaiSachDao(context);
    }


    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        TextView tvLoaiSach;
        ImageView imgDelete, imgEdit;
        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaiSach = itemView.findViewById(R.id.tvTenLoai);
            imgDelete = itemView.findViewById(R.id.imgdeleteLoaiSach);
            imgEdit = itemView.findViewById(R.id.imgEditLoaiSach);
        }
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach, parent, false);
        LoaiSachViewHolder holder = new LoaiSachViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        LoaiSach loaiSach = list.get(position);
        holder.tvLoaiSach.setText(list.get(position).getTenLoai());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdate(loaiSach);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.delete(loaiSach.getMaLoai())){
                    Toast.makeText(context, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao.getAll());
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });  }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void openDialogUpdate(LoaiSach loaiSach){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_loai, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        updateLoai = view.findViewById( R.id.updateLoai );
        edtUpdateLoai = view.findViewById( R.id.edtUpdateLoai );
        updateLoai.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSach.setTenLoai( edtUpdateLoai.getText().toString() );
                if (dao.update( loaiSach ) ){
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
