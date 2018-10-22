package com.example.rioir.fat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by rioir on 10/20/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    Context mContext;
    List<Home> mData;
    Dialog dialog;
    DataHelper datamakanan;
    Cursor cursor;

    public RecyclerAdapter(Context mContext, List<Home> mData) {
        this.mContext = mContext;
        this.mData = mData;
        datamakanan = new DataHelper(FragmentHome.layarutama.getContext());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //dialog
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_home);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        vHolder.item_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView txt_dialog_nama = dialog.findViewById(R.id.dialog_nama);
                TextView txt_dialog_daerah = dialog.findViewById(R.id.dialog_daerah);
                txt_dialog_nama.setText(mData.get(vHolder.getAdapterPosition()).getMenu());
                txt_dialog_daerah.setText(mData.get(vHolder.getAdapterPosition()).getDaerah());
                dialog.show();
                Button btn_lihat = dialog.findViewById(R.id.dialog_btn_lihat);
                btn_lihat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent0 = new Intent(mContext, TampilData.class);
                        intent0.putExtra("menu",txt_dialog_nama.getText().toString());
                        mContext.startActivity(intent0);
                    }
                });
                Button btn_update = dialog.findViewById(R.id.dialog_btn_edit);
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(mContext, EditData.class);
                        intent1.putExtra("menu",txt_dialog_nama.getText().toString());
                        mContext.startActivity(intent1);
                    }
                });
                Button btn_delete = dialog.findViewById(R.id.dialog_btn_hapus);
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datamakanan = new DataHelper(FragmentHome.layarutama.getActivity());
                        SQLiteDatabase db = datamakanan.getWritableDatabase();
                        db.execSQL("DELETE FROM makanan where menu = '"+txt_dialog_nama.getText().toString()+"'");
                        Intent i = new Intent(mContext,MainActivity.class);
                        Intent intent0 = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent0);

                    }
                });
            }
        });
        return vHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txt_menu.setText(mData.get(position).getMenu());
        holder.txt_daerah.setText(mData.get(position).getDaerah());
        holder.txt_harga.setText(mData.get(position).getHarga());
//        holder.img.setImageResource(mData.get(position).getGambar());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_home;
        private TextView txt_menu;
        private TextView txt_daerah;
        private TextView txt_harga;
//        private ImageView  img;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_home = itemView.findViewById(R.id.iditem_home);
            txt_menu = itemView.findViewById(R.id.menu);
            txt_daerah = itemView.findViewById(R.id.daerah);
            txt_harga = itemView.findViewById(R.id.harga);
//            img = itemView.findViewById(R.id.gambar);
        }
    }
}
