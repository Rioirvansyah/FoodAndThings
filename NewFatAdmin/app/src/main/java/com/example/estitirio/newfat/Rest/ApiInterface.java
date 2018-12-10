package com.example.estitirio.newfat.Rest;

import com.example.estitirio.newfat.model.CRUDKategori;
import com.example.estitirio.newfat.model.CRUDMakanan;
import com.example.estitirio.newfat.model.CRUDWilayah;
import com.example.estitirio.newfat.model.GetKategori;
import com.example.estitirio.newfat.model.GetMakanan;
import com.example.estitirio.newfat.model.GetWilayah;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

/**
 * Created by rioir on 12/2/2018.
 */

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


//    @GET("makanan2/")
//    Call<GetMakanan> getMakanan();
//
//    @FormUrlEncoded
//    @POST("makanan2/")
//    Call<CRUDMakanan> postMakanan
//            (@Field("id_makanan") String idMakanan, @Field("menu_makanan") String menuMakanan,
//             @Field("harga_makanan") String hargaMakanan, @Field("deskripsi_makanan") String deskripsiMakanan,
//             @Field("id_kategori") String idKategori, @Field("id_wilayah") String idWilayah);
//
//    @FormUrlEncoded
//    @PUT("makanan2/")
//    Call<CRUDMakanan> putMakanan(
//            @Field("id_makanan") String idMakanan, @Field("menu_makanan") String menuMakanan,
//            @Field("harga_makanan") String hargaMakanan, @Field("deskripsi_makanan") String deskripsiMakanan,
//            @Field("id_kategori") String idKategori, @Field("id_wilayah") String idWilayah);
//
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "makanan2/", hasBody = true)
//    Call<CRUDMakanan> deleteMakanan(@Field("id_makanan") String idMakanan);

//    kategori

    @GET("kategori/")
    Call<GetKategori> getKategori();

    @FormUrlEncoded
    @POST("kategori/")
    Call<CRUDKategori> postKategori
            (@Field("id_kategori") String idKategori, @Field("nama_kategori") String namaKategori);

    @FormUrlEncoded
    @PUT("kategori/")
    Call<CRUDKategori> putKategori(
            @Field("id_kategori") String idKategori, @Field("nama_kategori") String namaKategori);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kategori/", hasBody = true)
    Call<CRUDKategori> deleteKategori(@Field("id_kategori") String idKategori);

    //wilayah

    @GET("wilayah/")
    Call<GetWilayah> getWilayah();

    @FormUrlEncoded
    @POST("wilayah/")
    Call<CRUDWilayah> postWilayah
            (@Field("id_wilayah") String idWilayah, @Field("nama_wilayah") String namaWilayah,
            @Field("kota") String kota);

    @FormUrlEncoded
    @PUT("wilayah/")
    Call<CRUDWilayah> putWilayah
            (@Field("id_wilayah") String idWilayah, @Field("nama_wilayah") String namaWilayah,
            @Field("kota") String kota);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "wilayah/", hasBody = true)
    Call<CRUDWilayah> deleteWilayah(@Field("id_wilayah") String idWilayah);
}
