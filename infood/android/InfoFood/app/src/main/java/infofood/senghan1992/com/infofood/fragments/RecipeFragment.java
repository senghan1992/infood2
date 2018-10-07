package infofood.senghan1992.com.infofood.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;

public class RecipeFragment extends Fragment {

    int cnt;

    ArrayList list;

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

        return view;
    }

    public static RecipeFragment create(int position){
        RecipeFragment fragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cnt",position);
        fragment.setArguments(bundle);
        return fragment;
    }

}
