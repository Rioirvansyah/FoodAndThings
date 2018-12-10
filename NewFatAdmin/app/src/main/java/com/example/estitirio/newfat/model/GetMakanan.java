package com.example.estitirio.newfat.model;

/**
 * Created by rioir on 12/2/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetMakanan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    private List<Makanan> result = new ArrayList<Makanan>();
    @SerializedName("message")
    String message;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Makanan> getResult() {
        return result;
    }

    public void setResult(List<Makanan> result) {
        this.result = result;
    }



}
