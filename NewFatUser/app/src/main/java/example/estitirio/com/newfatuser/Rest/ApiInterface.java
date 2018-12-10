package example.estitirio.com.newfatuser.Rest;

import example.estitirio.com.newfatuser.model.GetLogin;
import example.estitirio.com.newfatuser.model.GetMakanan;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    /********* MAKANAN *********/
    @GET("makanan/all")
    Call<GetMakanan> getMakanan();

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> postMakanan(
            @Part MultipartBody.Part file,
            @Part("menu_makanan") RequestBody menuMakanan,
            @Part("harga_makanan") RequestBody hargaMakanan,
            @Part("deskripsi_makanan") RequestBody deskripsiMakanan,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> putMakanan(
            @Part MultipartBody.Part file,
            @Part("id_makanan") RequestBody idMakanan,
            @Part("menu_makanan") RequestBody menuMakanan,
            @Part("harga_makanan") RequestBody hargaMakanan,
            @Part("deskripsi_makanan") RequestBody deskripsiMakanan,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("makanan/all")
    Call<GetMakanan> deleteMakanan(
            @Part("id_makanan") RequestBody idMakanan,
            @Part("action") RequestBody action
    );

    //login
    @FormUrlEncoded
    @POST("user/login")
    Call<GetLogin> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

}
