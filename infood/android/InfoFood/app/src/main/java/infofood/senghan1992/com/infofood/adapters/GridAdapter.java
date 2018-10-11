package infofood.senghan1992.com.infofood.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.ServerInfo.ServerInfo;
import infofood.senghan1992.com.infofood.vo.FoodVO;

public class GridAdapter extends BaseAdapter {

    ArrayList<FoodVO> list;
    Context mContext;

    public GridAdapter(Context mContext, ArrayList<FoodVO> list){
        this.mContext = mContext;
        this.list = list;
        Log.d("그리드 어댑터에 넘어온 리스트 갯수 값", list.size()+"");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            convertView.setLayoutParams(new GridView.LayoutParams(300,300));
            imageView = convertView.findViewById(R.id.grid_image_view);
            Glide.with(mContext).load(ServerInfo.SERVER_IP_PHOTO + list.get(position).getImage()).into(imageView);

            Log.d("그리드 어댑터 이미지 값", ServerInfo.SERVER_IP_PHOTO + list.get(position).getImage());
        }
        return convertView;
    }
}
