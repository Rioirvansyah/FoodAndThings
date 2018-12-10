package com.example.estitirio.newfat.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rioir on 12/3/2018.
 */

public class GetWilayah {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Wilayah> listDataWilayah;
    @SerializedName("message")
    String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Wilayah> getListDataWilayah() {
        return listDataWilayah;
    }

    public void setListDataWilayah(List<Wilayah> listDataWilayah) {
        this.listDataWilayah = listDataWilayah;
    }

}
