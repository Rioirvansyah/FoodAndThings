package com.example.estitirio.newfat.model;

/**
 * Created by rioir on 12/3/2018.
 */
import com.google.gson.annotations.SerializedName;

public class Wilayah {
    @SerializedName("id_wilayah")
    private String id_wilayah;
    @SerializedName("nama_wilayah")
    private String nama_wilayah;
    @SerializedName("kota")
    private String kota;

    public Wilayah(String id_wilayah, String nama_wilayah, String kota) {
        this.id_wilayah = id_wilayah;
        this.nama_wilayah = nama_wilayah;
        this.kota = kota;
    }

    public String getId_wilayah() {
        return id_wilayah;
    }

    public void setId_wilayah(String id_wilayah) {
        this.id_wilayah = id_wilayah;
    }

    public String getNama_wilayah() {
        return nama_wilayah;
    }

    public void setNama_wilayah(String nama_wilayah) {
        this.nama_wilayah = nama_wilayah;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
