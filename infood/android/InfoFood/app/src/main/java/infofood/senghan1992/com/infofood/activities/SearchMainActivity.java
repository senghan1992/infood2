package infofood.senghan1992.com.infofood.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.adapters.SearchGridAdapter;
import infofood.senghan1992.com.infofood.utils.NetRetrofit;
import infofood.senghan1992.com.infofood.vo.FoodVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMainActivity extends AppCompatActivity {

    String station;
    ArrayList<FoodVO> search_list;

    //GridView 필요 조건
    GridView search_gridview;
    SearchGridAdapter gridAdapter;

    TextView search_result_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        station = getIntent().getStringExtra("station");
        search_gridview = findViewById(R.id.search_gridview);
        search_result_txt = findViewById(R.id.search_result_txt);
        search_result_txt.setText("\"요청하신 " + station + " 역 근처에 대한 결과\"");
        search_list = new ArrayList<>();

        //검색어로 리스트 가져오기
        new SearchTask().execute(station);

    }

    //검색어를 가지고 서버로 보내고 그 검색어에 따른 결과를 받아온다
    class SearchTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            getSearch(strings[0]);
            return null;
        }
    }

    private void getSearch(String station){
        Call<JsonArray> res = NetRetrofit.getInstance().getService().search(station);
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
                    search_list.add(vo);
                }
                gridAdapter = new SearchGridAdapter(search_list, SearchMainActivity.this);
                search_gridview.setAdapter(gridAdapter);
                search_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //밑에 그리드 뷰로 보이는 사진들 클릭시 일어나는 일들
                        Intent intent = new Intent(SearchMainActivity.this, DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("image",search_list.get(position).getImage());
                        bundle.putString("subway",search_list.get(position).getSubway());
                        bundle.putString("user_nikname",search_list.get(position).getUser_nikname());
                        bundle.putString("regidate",search_list.get(position).getRegidate());
                        bundle.putInt("user_idx",search_list.get(position).getUser_idx());
                        bundle.putString("food",search_list.get(position).getFood());
                        bundle.putString("content",search_list.get(position).getContent());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        //finish();
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }
}
