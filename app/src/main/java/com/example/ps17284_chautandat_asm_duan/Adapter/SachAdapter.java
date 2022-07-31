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

import com.example.ps17284_chautandat_asm_duan.Dao.SachDao;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

   ArrayList<Sach> list;
   Context context;
   SachDao dao;
    EditText edtUpdateTenSach, edtUpdateTacGia, edtUpdateGia;
    Button updateSach;

   public SachAdapter(ArrayList<Sach> list, Context context){
       this.list = list;
       this.context = context;
       dao = new SachDao(context);
   }

    public class SachViewHolder extends RecyclerView.ViewHolder {
       TextView tvTenSach, tvTacGia, tvGia;
       ImageView deleteSach, imgUpdateSach;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvGia = itemView.findViewById(R.id.tvGia);
            deleteSach = itemView.findViewById(R.id.imgdeleteSach);
            imgUpdateSach = itemView.findViewById(R.id.imgEditSach);

        }
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        SachViewHolder holder = new SachViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
       Sach sach = list.get(position);
       holder.tvTenSach.setText(list.get(position).getTen());
       holder.tvTacGia.setText(list.get(position).getTacgia());
       holder.tvGia.setText(list.get(position).getGia());
       holder.imgUpdateSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdate(sach);
            }
        });
       holder.deleteSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.delete(sach.getMa())){
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

    private void openDialogUpdate(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        edtUpdateTenSach = view.findViewById( R.id.edtUpdateTenSach );
        edtUpdateTacGia = view.findViewById( R.id.edtUpdateTacGia );
        edtUpdateGia = view.findViewById( R.id.edtUpdateGia );
        updateSach = view.findViewById( R.id.updateSach );
        updateSach.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach.setTen( edtUpdateTenSach.getText().toString() );
                sach.setTacgia( edtUpdateTacGia.getText().toString() );
                sach.setGia( edtUpdateGia.getText().toString() );
                if (dao.update( sach ) ){
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
