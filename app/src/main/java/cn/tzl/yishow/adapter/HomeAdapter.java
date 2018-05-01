package cn.tzl.yishow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.tzl.yishow.R;
import cn.tzl.yishow.bean.Comment;
import cn.tzl.yishow.bean.Info;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Info> mlist;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvContent,tvTime;
        ImageView iv_item;
        private ViewHolder(View view){
            super(view);
            tvTitle=view.findViewById(R.id.item_home_title);
            tvContent=view.findViewById(R.id.item_home_content);
            tvTime=view.findViewById(R.id.item_home_time);
            iv_item=view.findViewById(R.id.iv_item);
        }
    }

    public HomeAdapter(List mList){
        this.mlist=mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info info =(Info) mlist.get(position);
        holder.tvTitle.setText( info.getTitle());
        holder.tvContent.setText(info.getContent());
        holder.tvTime.setText(info.getUpdatedAt());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
