package infofood.senghan1992.com.infofood.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitService {
    //로그인
    @POST("login")
    Call<JsonObject> login(
            @Query("email")String email, @Query("pwd")String pwd);

    //food정보 올리기
    @Multipart
    @POST("upload_food")
    Call<ResponseBody> upload_food(@Query("subway")String subway,
                                   @Query("content")String content,
                                   @Query("food")String food,
                                   @Query("user_idx")String user_idx,
                                   @Query("user_nikname")String user_nikname,
                                   @Part MultipartBody.Part file);
    //tip정보 올리기
    @Multipart
    @POST("upload_tip")
    Call<ResponseBody> upload_content(
            @Part List<MultipartBody.Part> images,
            @Query("contents_list") Object[] tmp,
            @Query("title")String title,
            @Query("user_nikname")String user_nikname
    );

    //tip리스트 가져오기
    @POST("home_tip")
    Call<JsonArray> home_tip();

    //검색할때 자동완성을 위한 지하철역 정보 가져오기
    @POST("station")
    Call<JsonArray> station();

    //subway를 parameter로 subway를 포함한 food 정보 다 가져오기
    @POST("food_station")
    Call<JsonArray> getFoodList_station(@Query("subway")String subway);

    //검색어를 parameter로 보내서 해당역 맛집 정보 가져오기
    @POST("search")
    Call<JsonArray> search(@Query("station")String station);

}
