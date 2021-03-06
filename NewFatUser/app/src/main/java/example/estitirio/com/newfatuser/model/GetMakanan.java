package example.estitirio.com.newfatuser.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rioir on 12/10/2018.
 */

public class GetMakanan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    private List<Makanan> result = new ArrayList<Makanan>();
    @SerializedName("message")
    String message;

    public GetMakanan(){

    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public List<Makanan> getResult() {
        return result;
    }
    public void setResult(List<Makanan> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
