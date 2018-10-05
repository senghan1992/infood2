package infofood.senghan1992.com.infofood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;

import infofood.senghan1992.com.infofood.R;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SearchFragment extends Fragment {

    int cnt;

    Button search_btn;
    EditText search_edit;

    ListView search_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cnt = getArguments().getInt("cnt");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search_btn = view.findViewById(R.id.search_btn);
        search_edit = view.findViewById(R.id.search_edit);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String search = search_edit.getText().toString();
                bundle.putString("search_term",search);
                /*Intent i = new Intent(getContext(),Search_resActivity.class);
                i.putExtras(bundle);
                startActivity(i);*/
            }
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search_list = view.findViewById(R.id.search_list);

        return view;
    }

    public static SearchFragment create(int position) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cnt", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void Excel(){
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            InputStream inputStream = getContext().getResources().getAssets().open("seoul_station.xlsx");
            workbook = Workbook.getWorkbook(inputStream);
            sheet = workbook.getSheet(0);
            int MaxColumn = 2, RowStart = 1, RowEnd = sheet.getColumn(MaxColumn - 1).length -1, ColumnStart = 0, ColumnEnd = sheet.getRow(2).length - 1;
            for(int row = RowStart;row <= RowEnd;row++) {
                String excelload = sheet.getCell(ColumnStart, row).getContents();
                //arrayAdapter.add(excelload);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            //가져온 결과를 ArrayList에 저장하여 Adapter 설정 해주기
            //search_list.setAdapter(arrayAdapter);
            workbook.close();
        }
    }

}
