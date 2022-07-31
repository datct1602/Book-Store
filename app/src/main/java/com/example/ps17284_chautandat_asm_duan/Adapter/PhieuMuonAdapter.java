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

import com.example.ps17284_chautandat_asm_duan.Dao.PhieuMuonDao;
import com.example.ps17284_chautandat_asm_duan.Dao.SachDao;
import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {

    ArrayList<PhieuMuon> list;
    Context context;
    Button add;
    PhieuMuonDao dao;
    EditText edtUpdateNguoiMuon, edtUpdateSachMuon, edtUpdateNgayMuon;
    Button updatePhieuMuon;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new PhieuMuonDao(context);
    }


    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);
        PhieuMuonAdapter.PhieuMuonViewHolder holder = new PhieuMuonAdapter.PhieuMuonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.tvNguoiMuon.setText(list.get(position).getNguoiMuon());
        holder.tvSachMuon.setText(list.get(position).getSachMuon());
        holder.tvNgay.setText(list.get(position).getNgay());
        holder.imgUpdatePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogUpdate(phieuMuon);
            }
        });
        holder.imgDeletePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dao.delete(phieuMuon.getMa())){
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

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        TextView tvNguoiMuon, tvSachMuon, tvNgay;
        ImageView imgDeletePhieuMuon, imgUpdatePhieuMuon;
        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNguoiMuon = itemView.findViewById(R.id.tvNguoiMuon);
            tvSachMuon = itemView.findViewById(R.id.tvSachMuon);
            tvNgay = itemView.findViewById(R.id.tvNgayMuon);
            imgDeletePhieuMuon = itemView.findViewById(R.id.imgdeletePhieuMuon);
            imgUpdatePhieuMuon = itemView.findViewById(R.id.imgEditPhieuMuon);

        }
    }

    private void openDialogUpdate(PhieuMuon phieuMuon){
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_phieumuon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        edtUpdateNguoiMuon = view.findViewById( R.id.edtUpdateNguoiMuon );
        edtUpdateSachMuon = view.findViewById( R.id.edtUpdateSachMuon );
        edtUpdateNgayMuon = view.findViewById( R.id.edtUpdateNgayMuon );
        updatePhieuMuon = view.findViewById( R.id.updatePhieuMuon );
        updatePhieuMuon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuon.setNguoiMuon( edtUpdateNguoiMuon.getText().toString() );
                phieuMuon.setSachMuon( edtUpdateSachMuon.getText().toString() );
                phieuMuon.setNgay( edtUpdateNgayMuon.getText().toString() );
                if (dao.update( phieuMuon ) ){
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
