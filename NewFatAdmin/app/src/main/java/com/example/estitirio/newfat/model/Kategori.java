package com.example.estitirio.newfat.model;

/**
 * Created by rioir on 12/3/2018.
 */

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("nama_kategori")
    private String nama_kategori;

    public Kategori(String id_kategori, String nama_kategori) {
        this.id_kategori = id_kategori;
        this.nama_kategori = nama_kategori;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }
}
