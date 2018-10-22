package com.example.rioir.fat;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rioir on 10/19/2018.
 */

public class FragmentHome extends Fragment {
    View view;
    private RecyclerView myRecyclerView;
    private List<Home> isiHome;
    DataHelper datamakanan;
    Cursor cursor;
    public static FragmentHome layarutama;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        isiHome = new ArrayList<>();
//        isiHome.add(new Home("Batagor","Sawojajar","Rp 20000,-",R.drawable.g3));
//        isiHome.add(new Home("Siomay","Kalpataru","Rp 10000,-",R.drawable.g5));
//        isiHome.add(new Home("Batagor","Sawojajar","Rp 20000,-",R.drawable.g3));
//        isiHome.add(new Home("Siomay","Kalpataru","Rp 10000,-",R.drawable.g5));
//        isiHome.add(new Home("Batagor","Sawojajar","Rp 20000,-",R.drawable.g3));
//        isiHome.add(new Home("Siomay","Kalpataru","Rp 10000,-",R.drawable.g5));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layarutama = this;
        tampilkanList();
        view = inflater.inflate(R.layout.activity_home, container, false);
        myRecyclerView = view.findViewById(R.id.home_recyclerview);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),isiHome);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
        return view;


    }

    public void tampilkanList(){
        datamakanan = new DataHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = datamakanan.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM makanan", null);
        isiHome = new ArrayList<>();

        final String[] databaru = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            databaru[cc] = cursor.getString(1);
            String Menu = cursor.getString(1);
            String Asal = cursor.getString(2);
            String Harga = cursor.getString(3);
            isiHome.add(new Home(Menu, Asal, Harga));
        }

//            do{
//                Home home = new Home(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
//                isiHome.add(home);
//            }while(cursor.moveToNext());
//            datamakanan.close();;
//
//        for (int cc = 0; cc < cursor.getCount(); cc++){
//            cursor.moveToPosition(cc);
//            databaru[cc] = cursor.getString(1);
//        }
//
//        cursor.close();
    }
}
