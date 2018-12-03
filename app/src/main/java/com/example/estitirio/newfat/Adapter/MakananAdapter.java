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
import com.example.estitirio.newfat.makananDetail;
import com.example.estitirio.newfat.model.Makanan;

import java.util.List;

/**
 * Created by rioir on 12/3/2018.
 */

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.MyViewHolder>  {

    List<Makanan> mMakananList;

    public MakananAdapter(List<Makanan> makananList) {
        mMakananList = makananList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdMakanan.setText("Id Makanan :  " + mMakananList.get(position)
                .getId_makanan());
        holder.mTextViewMenuMakanan.setText("Menu Makanan :  " + mMakananList.get(position)
                .getMenu_makanan());
        holder.mTextViewDeskripsiMakanan.setText("Harga Makanan :  " + mMakananList.get(position)
                .getHarga_makanan());
        holder.mTextViewHargaMakanan.setText("Deskripsi Makanan :  " + mMakananList.get(position)
                .getDeskripsi_makanan());
        holder.mTextViewIdKategori.setText("ID Kategori :  " + String.valueOf(mMakananList.get
                (position).getId_kategori()));
        holder.mTextViewIdKategori.setText("ID Wilayah :  " + String.valueOf(mMakananList.get
                (position).getId_wilayah()));

        if (mMakananList.get(position).getPhotoUrl() != null ){
//            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
//                    .into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(mMakananList.get
                    (position).getPhotoUrl())
                    .into(holder.mPhotoURL);
        } else {
//          Picasso.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.user).into(holder
                    .mPhotoURL);
        }



//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent mIntent = new Intent(view.getContext(), makananDetail.class);
//                mIntent.putExtra("id_makanan",mMakananList.get(position).getId_makanan());
//                mIntent.putExtra("menu_makanan",mMakananList.get(position).getMenu_makanan());
//                mIntent.putExtra("harga_makanan",String.valueOf(mMakananList.get(position).getHarga_makanan()));
//                mIntent.putExtra("deskripsi_makanan",mMakananList.get(position).getDeskripsi_makanan());
//                mIntent.putExtra("id_kategori",mMakananList.get(position).getId_kategori());
//                mIntent.putExtra("id_wilayah",mMakananList.get(position).getId_wilayah());
//                view.getContext().startActivity(mIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mMakananList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdMakanan, mTextViewMenuMakanan, mTextViewHargaMakanan, mTextViewDeskripsiMakanan, mTextViewIdKategori, mTextViewIdWilayah;
        ImageView mPhotoURL;
        public MyViewHolder(View itemView) {
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
