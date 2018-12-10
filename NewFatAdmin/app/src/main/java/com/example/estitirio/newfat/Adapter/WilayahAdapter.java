package com.example.estitirio.newfat.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.estitirio.newfat.R;
import com.example.estitirio.newfat.model.Wilayah;

import java.util.List;

/**
 * Created by rioir on 12/3/2018.
 */

public class WilayahAdapter extends RecyclerView.Adapter<WilayahAdapter.MyViewHolder> {
    List<Wilayah> mWilayahList;

    public WilayahAdapter(List<Wilayah> wilayahList) {
        mWilayahList = wilayahList;
    }

    @Override
    public WilayahAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wilayah,parent,false);
        WilayahAdapter.MyViewHolder mViewHolder = new WilayahAdapter.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(WilayahAdapter.MyViewHolder holder, final int position) {
        holder.mTextViewIdWilayah.setText("Id Wilayah :  " + mWilayahList.get(position)
                .getId_wilayah());
        holder.mTextViewNamaWilayah.setText("Nama Wilayah :  " + mWilayahList.get(position)
                .getNama_wilayah());
        holder.mTextViewKota.setText("Kota :  " + mWilayahList.get(position)
                .getKota());

//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent mIntent = new Intent(view.getContext(), makananDetail.class);
//                mIntent.putExtra("id_makanan",mWilayahList.get(position).getId_makanan());
//                mIntent.putExtra("menu_makanan",mWilayahList.get(position).getMenu_makanan());
//                mIntent.putExtra("harga_makanan",String.valueOf(mWilayahList.get(position).getHarga_makanan()));
//                mIntent.putExtra("deskripsi_makanan",mWilayahList.get(position).getDeskripsi_makanan());
//                mIntent.putExtra("id_kategori",mWilayahList.get(position).getId_kategori());
//                mIntent.putExtra("id_wilayah",mWilayahList.get(position).getId_wilayah());
//                view.getContext().startActivity(mIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mWilayahList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdWilayah, mTextViewNamaWilayah, mTextViewKota;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdWilayah = (TextView) itemView.findViewById(R.id.tvIdWilayah);
            mTextViewNamaWilayah = (TextView) itemView.findViewById(R.id.tvNamaWilayah);
            mTextViewKota = (TextView) itemView.findViewById(R.id.tvKota);
        }
    }
}
