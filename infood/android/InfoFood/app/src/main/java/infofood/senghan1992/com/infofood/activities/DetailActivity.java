package infofood.senghan1992.com.infofood.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.ServerInfo.ServerInfo;
import infofood.senghan1992.com.infofood.adapters.GridAdapter;
import infofood.senghan1992.com.infofood.utils.NetRetrofit;
import infofood.senghan1992.com.infofood.vo.FoodVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView detail_nikname, detail_regidate, detail_subway, detail_content, detail_around_txt;
    ImageView detail_image;
    String nikname, regidate, subway, image, content;
    int user_idx;

    GridView detail_gridView;
    ArrayList<FoodVO> list;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        nikname = bundle.getString("user_nikname");
        regidate = bundle.getString("regidate");
        subway = bundle.getString("subway");
        image = bundle.getString("image");
        content = bundle.getString("content");
        user_idx = bundle.getInt("user_idx");

        //검색
        detail_nikname = findViewById(R.id.detail_nikname);
        detail_regidate = findViewById(R.id.detail_regidate);
        detail_subway = findViewById(R.id.detail_subway);
        detail_image = findViewById(R.id.detail_image);
        detail_content = findViewById(R.id.detail_content);
        detail_around_txt = findViewById(R.id.detail_around_txt);
        detail_gridView = findViewById(R.id.detail_gridView);

        detail_nikname.setText(nikname);
        detail_regidate.setText(regidate.substring(0,10));
        detail_subway.setText(subway);
        detail_content.setText(content);
        Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+image).into(detail_image);
        detail_around_txt.setText(subway + " " + detail_around_txt.getText().toString());

        list = new ArrayList<>();
        new Task().execute();

    }

    //rerofit2를 사용하여 subway 주변 맛집 정보를 가져와보자
    private void getFoodList(){
        Call<JsonArray> res = NetRetrofit.getInstance().getService().getFoodList_station(subway);
        res.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray array = response.body().getAsJsonArray();
                for(int i=0;i<array.size();i++){
                    JsonObject object = (JsonObject) array.get(i);
                    FoodVO vo = new FoodVO();
                    String idx = object.get("idx").toString();
                    idx = idx.replace("\"","");
                    String user_idx = object.get("user_idx").toString();
                    user_idx = user_idx.replace("\"","");
                    String user_nikname = object.get("user_nikname").toString();
                    user_nikname = user_nikname.replace("\"","");
                    String image = object.get("image").toString();
                    image = image.replace("\"","");
                    String subway = object.get("subway").toString();
                    subway = subway.replace("\"","");
                    String food = object.get("food").toString();
                    food = food.replace("\"","");
                    String content = object.get("content").toString();
                    content = content.replace("\"","");
                    String regidate = object.get("regidate").toString();
                    regidate = regidate.replace("\"","");

                    vo.setIdx(Integer.parseInt(idx));
                    vo.setUser_idx(Integer.parseInt(user_idx));
                    vo.setUser_nikname(user_nikname);
                    vo.setImage(image);
                    vo.setSubway(subway);
                    vo.setFood(food);
                    vo.setContent(content);
                    vo.setRegidate(regidate);
                    list.add(vo);
                }
                Log.d("디테일 화면 넘어오는 값 확인", list.get(0).getFood());
                gridAdapter = new GridAdapter(DetailActivity.this, list);
                detail_gridView.setAdapter(gridAdapter);
                detail_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //밑에 그리드 뷰로 보이는 사진들 클릭시 일어나는 일들
                        Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("image",list.get(position).getImage());
                        bundle.putString("subway",list.get(position).getSubway());
                        bundle.putString("user_nikname",list.get(position).getUser_nikname());
                        bundle.putString("regidate",list.get(position).getRegidate());
                        bundle.putInt("user_idx",list.get(position).getUser_idx());
                        bundle.putString("food",list.get(position).getFood());
                        bundle.putString("content",list.get(position).getContent());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        //Toast.makeText(getApplicationContext(),list.get(position).getFood(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }


    class Task extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            getFoodList();
            return null;
        }
    }//Task()
}
