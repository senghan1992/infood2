package infofood.senghan1992.com.infofood.utils;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import infofood.senghan1992.com.infofood.vo.TipVO;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {
    @POST("login")
    Call<JsonObject> login(
            @Query("email")String email, @Query("pwd")String pwd);

    @Multipart
    @POST("upload_food")
    Call<ResponseBody> upload_food(@Query("subway")String subway,
                                   @Query("content")String content,
                                   @Query("food")String food,
                                   @Query("user_idx")String user_idx,
                                   @Query("user_nikname")String user_nikname,
                                   @Part MultipartBody.Part file);

    //@Multipart
    @FormUrlEncoded
    @POST("upload_tip")
    Call<ResponseBody> upload_content(
            @FieldMap Map<String, MultipartBody.Part> tip_list,
            @FieldMap Map<String, String> tip_content_list,
            @Query("title")String title
    );


}
