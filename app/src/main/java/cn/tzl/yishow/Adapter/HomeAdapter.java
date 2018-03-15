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
 * Created by Tanzl on 2018/1/27.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List mlist;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView show;
        ImageView iv_item;
        public ViewHolder(View view){
            super(view);
            show=view.findViewById(R.id.item_home_text);
            iv_item=view.findViewById(R.id.iv_item);
        }
    }

    public HomeAdapter(List mlist){
        this.mlist=mlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.show.setText( mlist.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }







}
