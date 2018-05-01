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
 * Created by Tanzl on 2018/2/8.
 * Class CommentBean:
 */

public class CategoryContentAdapter extends RecyclerView.Adapter<CategoryContentAdapter.ViewHolder>{

    private List<String> list;

    public CategoryContentAdapter(List<String> list){
        this.list = list;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cate_image;
        TextView cate_name;

        private ViewHolder(View view) {
            super(view);
            cate_image=view.findViewById(R.id.item_cate_image);
            cate_name=view.findViewById(R.id.item_cate_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_category,parent,false);
        return new CategoryContentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.cate_image.setBackgroundResource(R.mipmap.cloths);
            holder.cate_name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
