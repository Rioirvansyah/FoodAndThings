package com.example.estitirio.newfat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rioir on 12/3/2018.
 */
import com.google.gson.annotations.SerializedName;

public class CRUDKategori {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kategori mKategori;
    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Kategori getKategori() {
        return mKategori;
    }

    public void setKategori(Kategori kategori) {
        mKategori = kategori;
    }


}
