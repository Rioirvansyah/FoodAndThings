package com.example.estitirio.newfat.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.estitirio.newfat.R;
import com.example.estitirio.newfat.kategoriDetail;
import com.example.estitirio.newfat.makananDetail;
import com.example.estitirio.newfat.model.Kategori;

import java.util.List;

/**
 * Created by rioir on 12/3/2018.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder>{
    List<Kategori> mKategoriList;

    public KategoriAdapter(List<Kategori> kategoriList) {
        mKategoriList = kategoriList;
    }

    @Override
    public KategoriAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori,parent,false);
        KategoriAdapter.MyViewHolder mViewHolder = new KategoriAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(KategoriAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewIdKategori.setText("Id Kategori :  " + mKategoriList.get(position)
                .getId_kategori());
        holder.mTextViewNamaKategori.setText("Nama Kategori :  " + mKategoriList.get(position)
                .getNama_kategori());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent mIntent = new Intent(view.getContext(), kategoriDetail.class);
                mIntent.putExtra("id_kategori",mKategoriList.get(position).getId_kategori());
                mIntent.putExtra("nama_kategori",mKategoriList.get(position).getNama_kategori());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKategoriList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdKategori, mTextViewNamaKategori;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            mTextViewNamaKategori = (TextView) itemView.findViewById(R.id.tvNamaKategori);
        }
    }
}
