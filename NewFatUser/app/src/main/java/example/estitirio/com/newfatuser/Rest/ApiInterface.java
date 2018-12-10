package example.estitirio.com.newfatuser.Rest;

import example.estitirio.com.newfatuser.model.GetLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    //login
    @FormUrlEncoded
    @POST("user/login")
    Call<GetLogin> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

}
