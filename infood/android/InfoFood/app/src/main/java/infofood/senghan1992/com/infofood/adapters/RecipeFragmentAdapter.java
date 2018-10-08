package infofood.senghan1992.com.infofood.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import infofood.senghan1992.com.infofood.R;
import infofood.senghan1992.com.infofood.ServerInfo.ServerInfo;
import infofood.senghan1992.com.infofood.vo.Content_tipVO;

public class RecipeFragmentAdapter extends RecyclerView.Adapter<RecipeFragmentAdapter.MyViewHolder> {

    ArrayList<Content_tipVO> list;
    Context mContext;

    public RecipeFragmentAdapter(ArrayList<Content_tipVO> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView tip_recycler_item1;
        TextView tip_recycler_item2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tip_recycler_item1 = itemView.findViewById(R.id.tip_recycler_item1);
            tip_recycler_item2 = itemView.findViewById(R.id.tip_recycler_item2);
        }
    }

    @NonNull
    @Override
    public RecipeFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_recycler_list_item,
                parent,false);

        MyViewHolder vh = new MyViewHolder(itemVIew);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeFragmentAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+
                list.get(position).getContent_image1()).into(holder.tip_recycler_item1);
        Log.d("RECIPE 넘어오는 값", list.get(position).getContent_image1());
        holder.tip_recycler_item2.setText(list.get(position).getContent1());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
