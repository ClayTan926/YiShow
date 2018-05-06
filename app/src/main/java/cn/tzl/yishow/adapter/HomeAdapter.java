package cn.tzl.yishow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tzl.yishow.R;
import cn.tzl.yishow.bean.Comment;
import cn.tzl.yishow.bean.Info;
import cn.tzl.yishow.module_FasionInfo.ShowInfoActivity;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener {

    private List<Info> mlist;
    private Context context;
    private String url;
    private View view;
    private String title;
    private Integer num;
    private Map<Integer, String> map = new HashMap();
    ;

    public HomeAdapter(List<Info> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Log.e("adapter", "onClick: " + url + " title:" + title + "position" + num);
        Intent intent = new Intent(context, ShowInfoActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvContent, tvTime;
        ImageView ivShowImg;


        private ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.item_home_title);
            tvContent = view.findViewById(R.id.item_home_content);
            tvTime = view.findViewById(R.id.item_home_time);
            ivShowImg = view.findViewById(R.id.iv_showImg);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Info info = mlist.get(position);
        holder.tvTitle.setText(info.getTitle());
        holder.tvContent.setText(String.valueOf("\u3000\u3000" + info.getContent()));
        holder.tvTime.setText(info.getUpdatedAt());
        if (info.getShowImg() != null && !info.getShowImg().equals("")) {
            holder.ivShowImg.setVisibility(View.VISIBLE);
            Glide.with(view)
                    .load(info.getShowImg())
                    .into(holder.ivShowImg);
        } else {
            holder.ivShowImg.setVisibility(View.GONE);
        }
        title = info.getTitle();
        url = info.getUrl();
        num = position;
        //holder.itemView.setOnClickListener(this);
        //holder.ivShowImg.setOnClickListener(this);
       /* holder.tvContent.setOnClickListener(this);*/
        view.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


}
