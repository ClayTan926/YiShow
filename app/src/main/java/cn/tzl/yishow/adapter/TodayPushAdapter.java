package cn.tzl.yishow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.tzl.yishow.R;

/**
 * Created by Tanzl on 2018/2/19.
 * Class CommentBean:
 */

public class TodayPushAdapter extends RecyclerView.Adapter<TodayPushAdapter.ViewHolder> {


    private List<Integer> list;
    private String text;
    public TodayPushAdapter(List<Integer> list, String text) {
        this.list=list;
        this.text=text;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView allClothes;
        ImageView clothesOne;
        ImageView clothesTwo;
        ImageView clothesThree;
        TextView clothesdDescription;
        private ViewHolder(View itemView) {
            super(itemView);
            allClothes=itemView.findViewById(R.id.iv_allclothes);
            clothesOne=itemView.findViewById(R.id.iv_clothesone);
            clothesTwo=itemView.findViewById(R.id.iv_clothestwo);
            clothesThree=itemView.findViewById(R.id.iv_clothesthree);
            clothesdDescription=itemView.findViewById(R.id.tv_description);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_todaypush,parent,false);
        return new TodayPushAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodayPushAdapter.ViewHolder holder, int position) {
        holder.allClothes.setImageResource(list.get(position));
        holder.clothesOne.setImageResource(list.get(position));
        holder.clothesTwo.setImageResource(list.get(position));
        holder.clothesThree.setImageResource(list.get(position));
        holder.clothesdDescription.setText(text);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }


}
