package com.example.estitirio.newfat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rioir on 12/3/2018.
 */

public class CRUDWilayah {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Wilayah mWilayah;
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

    public Wilayah getWilayah() {
        return mWilayah;
    }

    public void setWilayah(Wilayah wilayah) {
        mWilayah = wilayah;
    }
}
