package infofood.senghan1992.com.infofood.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.ServerInfo.ServerInfo;
import infofood.senghan1992.com.infofood.vo.FoodVO;

public class SearchGridAdapter extends BaseAdapter {

    ArrayList<FoodVO> list;
    Context mContext;

    public SearchGridAdapter(ArrayList<FoodVO> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
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
        TextView textView;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
            convertView.setLayoutParams(new GridView.LayoutParams(300,300));
            imageView = convertView.findViewById(R.id.grid_image_view);
            textView = convertView.findViewById(R.id.grid_text_view);
            textView.setText(list.get(position).getFood());
            Glide.with(mContext).load(ServerInfo.SERVER_IP_PHOTO + list.get(position).getImage()).into(imageView);

            Log.d("그리드 어댑터 이미지 값", ServerInfo.SERVER_IP_PHOTO + list.get(position).getImage());
        }
        return convertView;
    }
}
