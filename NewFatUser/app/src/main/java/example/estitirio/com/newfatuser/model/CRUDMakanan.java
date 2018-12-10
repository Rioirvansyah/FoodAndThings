package example.estitirio.com.newfatuser.model;

/**
 * Created by rioir on 12/10/2018.
 */

import com.google.gson.annotations.SerializedName;
public class CRUDMakanan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Makanan mMakanan;
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

    public Makanan getMakanan() {
        return mMakanan;
    }

    public void setMakanan(Makanan makanan) {
        mMakanan = makanan;
    }

}