package infofood.senghan1992.com.infofood.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.adapters.SearchFragmentAdapter;
import infofood.senghan1992.com.infofood.utils.NetRetrofit;
import infofood.senghan1992.com.infofood.utils.RetrofitService;
import infofood.senghan1992.com.infofood.vo.StationVO;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    int cnt;

    Button search_btn;
    AutoCompleteTextView search_edit;

    //지하철 역 정보를 받아올 List
    ArrayList<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cnt = getArguments().getInt("cnt");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search_edit = view.findViewById(R.id.search_edit);

        search_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search_edit.getRight() - search_edit.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        String search_string = search_edit.getText().toString().trim();
                        //검색어를 가지고 db에서 해당하는 정보를 가져오는 것을 해야한다

                        //그리고 검색후 페이지로 이동
                        /////////////////////////////////////////////////

                        return true;
                    }
                }
                return false;
            }
        });

        list = new ArrayList<>();
        new Task().execute();

        return view;
    }

    class Task extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            getStation();
            return null;
        }

    }

    private void getStation(){

        Call<JsonArray> res = NetRetrofit.getInstance().getService().station();
        res.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray array = response.body().getAsJsonArray();
                Log.i("검색화면 넘어오는 리스트 갯수", array.size()+"");
                for(int i=0;i<array.size();i++){
                    JsonObject object = (JsonObject) array.get(i);
                    StationVO vo = new StationVO();
                    String station_name = object.get("station_name").toString();
                    station_name = station_name.replace("\"", "");
                    vo.setStation_name(station_name);
                    list.add(vo.getStation_name());
                }
                Log.i("검색화면 넘어오는 리스트 추가 갯수" , list.size()+"");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, list);
                search_edit.setThreshold(1);
                search_edit.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });

    }

    public static SearchFragment create(int position) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cnt", position);
        fragment.setArguments(bundle);
        return fragment;
    }


}
