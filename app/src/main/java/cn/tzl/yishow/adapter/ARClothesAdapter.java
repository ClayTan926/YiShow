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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.tzl.yishow.R;
import cn.tzl.yishow.module_Ar.ARActivity;

/**
 * Created by Tanzl on 2018/5/4.
 * Class Comment:
 */

public class ARClothesAdapter extends RecyclerView.Adapter<ARClothesAdapter.ViewHolder> {

    private List<Integer> dataList = new ArrayList<>();
    private View view;
    private Context context;

    public ARClothesAdapter( Context context,List<Integer> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView clothes;


        private ViewHolder(View view) {
            super(view);
            clothes = view.findViewById(R.id.iv_selClothes);
        }
    }

    @Override
    public ARClothesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ar_clothes, parent, false);
        return new ARClothesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.clothes.setImageResource( dataList.get(position));
        holder.clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ClothesAdapter", "onViewClicked: 跳转AR页面");
                Intent intent = new Intent(view.getContext(), ARActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
