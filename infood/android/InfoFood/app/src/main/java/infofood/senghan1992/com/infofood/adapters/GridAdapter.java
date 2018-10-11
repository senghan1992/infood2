package infofood.senghan1992.com.infofood.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.vo.FoodVO;

public class GridAdapter extends BaseAdapter {

    ArrayList<FoodVO> list;
    Context mContext;

    public GridAdapter(Context mContext, ArrayList<FoodVO> list){
        mContext = this.mContext;
        list = this.list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
