package com.example.estitirio.newfat.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.estitirio.newfat.R;
import com.example.estitirio.newfat.Rest.ApiClient;
import com.example.estitirio.newfat.editMakanan;
import com.example.estitirio.newfat.makananDetail;
import com.example.estitirio.newfat.model.Makanan;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rioir on 12/3/2018.
 */

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MakananViewHolder>  {
    List<Makanan> listMakanan;

    public MakananAdapter(List<Makanan> listMakanan) {
        this.listMakanan = listMakanan;
    }

    public MakananAdapter.MakananViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan, parent, false);
        MakananViewHolder mHolder = new MakananViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MakananAdapter.MakananViewHolder holder, final int position) {
        holder.mTextViewIdMakanan.setText("Id Makanan :  " + listMakanan.get(position)
                .getId_makanan());
        holder.mTextViewMenuMakanan.setText("Menu Makanan :  " + listMakanan.get(position)
                .getMenu_makanan());
        holder.mTextViewDeskripsiMakanan.setText("Harga Makanan :  " + listMakanan.get(position)
                .getHarga_makanan());
        holder.mTextViewHargaMakanan.setText("Deskripsi Makanan :  " + listMakanan.get(position)
                .getDeskripsi_makanan());

        if (listMakanan.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listMakanan.get(position).getPhotoUrl())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(listMakanan.get(position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {
          Picasso.with(holder.itemView.getContext()).load(R.drawable.user).into(holder.mPhotoURL);
//            Glide.with(holder.itemView.getContext()).load(R.drawable.user).into(holder
//                    .mPhotoURL);
        }

        holder.mTextViewIdKategori.setText("ID Kategori :  " + listMakanan.get(position)
                .getId_kategori());
        holder.mTextViewIdWilayah.setText("ID Wilayah :  " + listMakanan.get(position)
                .getId_wilayah());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent mIntent = new Intent(view.getContext(), editMakanan.class);
                mIntent.putExtra("id_makanan",listMakanan.get(position).getId_makanan());
                mIntent.putExtra("menu_makanan",listMakanan.get(position).getMenu_makanan());
                mIntent.putExtra("harga_makanan",String.valueOf(listMakanan.get(position).getHarga_makanan()));
                mIntent.putExtra("deskripsi_makanan",listMakanan.get(position).getDeskripsi_makanan());
                mIntent.putExtra("photo_url",listMakanan.get(position).getPhotoUrl());
//                mIntent.putExtra("id_kategori",listMakanan.get(position).getId_kategori());
//                mIntent.putExtra("id_wilayah",listMakanan.get(position).getId_wilayah());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    public class MakananViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdMakanan, mTextViewMenuMakanan, mTextViewHargaMakanan, mTextViewDeskripsiMakanan, mTextViewIdKategori, mTextViewIdWilayah;
        ImageView mPhotoURL;
        public MakananViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgMakanan);
            mTextViewIdMakanan = (TextView) itemView.findViewById(R.id.tvIdMakanan);
            mTextViewMenuMakanan = (TextView) itemView.findViewById(R.id.tvMenuMakanan);
            mTextViewHargaMakanan = (TextView) itemView.findViewById(R.id.tvHargaMakanan);
            mTextViewDeskripsiMakanan = (TextView) itemView.findViewById(R.id.tvDeskripsiMakanan);
            mTextViewIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            mTextViewIdWilayah = (TextView) itemView.findViewById(R.id.tvIdWilayah);
        }
    }
}
