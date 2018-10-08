package infofood.senghan1992.com.infofood.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.adapters.RecipeFragmentAdapter;
import infofood.senghan1992.com.infofood.utils.NetRetrofit;
import infofood.senghan1992.com.infofood.vo.Content_tipVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment {

    int cnt;

    ArrayList<Content_tipVO> list;

    RecyclerView recycler_view;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cnt = getArguments().getInt("cnt");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container,
                false);

        list = new ArrayList<>();

        new Task().execute();

        recycler_view = view.findViewById(R.id.tip_recycler_view);
        recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(mLayoutManager);

        return view;
    }

    //retrofit2를 활용하여 서버에 넘길 것들
    private void home_tip(){
        Call<JsonArray> res = NetRetrofit.getInstance().getService().home_tip();
        res.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray array = response.body().getAsJsonArray();
                for (int i=0;i<array.size();i++){
                    JsonObject object = (JsonObject) array.get(i);

                    Content_tipVO vo = new Content_tipVO();
                    vo.setIdx(Integer.parseInt(object.get("idx").toString()));
                    vo.setUser_nikname(object.get("user_nikname").toString());
                    vo.setTitle(object.get("title").toString());
                    vo.setContent1(object.get("content1").toString());
                    vo.setContent2(object.get("content2").toString());
                    vo.setContent3(object.get("content3").toString());
                    vo.setContent4(object.get("content4").toString());
                    vo.setContent5(object.get("content5").toString());
                    String test = object.get("content_image1").toString();
                    test = test.replace("\"","");
                    vo.setContent_image1(test);
                    vo.setContent_image2(object.get("content_image2").toString());
                    vo.setContent_image3(object.get("content_image3").toString());
                    vo.setContent_image4(object.get("content_image4").toString());
                    vo.setContent_image5(object.get("content_image5").toString());
                    vo.setRegidate(object.get("regidate").toString());

                    list.add(vo);

                }
                mAdapter = new RecipeFragmentAdapter(list, getContext());
                recycler_view.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });

    }

    public static RecipeFragment create(int position){
        RecipeFragment fragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cnt",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    //retrofit2와 연동하기 위한 AsyncTask
    class Task extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            home_tip();
            return null;
        }
    }

}
