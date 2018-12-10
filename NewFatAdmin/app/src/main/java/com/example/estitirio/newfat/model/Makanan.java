package com.example.estitirio.newfat.model;

/**
 * Created by rioir on 12/2/2018.
 */

import com.google.gson.annotations.SerializedName;

public class Makanan {
    @SerializedName("id_makanan")
    private String id_makanan;
    @SerializedName("menu_makanan")
    private String menu_makanan;
    @SerializedName("harga_makanan")
    private int harga_makanan;
    @SerializedName("deskripsi_makanan")
    private String deskripsi_makanan;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;
    @SerializedName("id_kategori")
    private String id_kategori;
    @SerializedName("id_wilayah")
    private String id_wilayah;

    public Makanan(String id_makanan, String id_kategori, String menu_makanan, int harga_makanan, String deskripsi_makanan, String id_wilayah, String photoUrl, String action) {
        this.id_makanan = id_makanan;
        this.menu_makanan = menu_makanan;
        this.harga_makanan = harga_makanan;
        this.deskripsi_makanan = deskripsi_makanan;
        this.id_kategori = id_kategori;
        this.id_wilayah = id_wilayah;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getId_makanan() {
        return id_makanan;
    }

    public void setId_makanan(String id_makanan) {
        this.id_makanan = id_makanan;
    }

    public String getMenu_makanan() {
        return menu_makanan;
    }

    public void setMenu_makanan(String menu_makanan) {
        this.menu_makanan = menu_makanan;
    }

    public int getHarga_makanan() {
        return harga_makanan;
    }

    public void setHarga_makanan(int harga_makanan) {
        this.harga_makanan = harga_makanan;
    }

    public String getDeskripsi_makanan() {
        return deskripsi_makanan;
    }

    public void setDeskripsi_makanan(String deskripsi_makanan) {
        this.deskripsi_makanan = deskripsi_makanan;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getId_wilayah() {
        return id_wilayah;
    }

    public void setId_wilayah(String id_wilayah) {
        this.id_wilayah = id_wilayah;
    }


}
