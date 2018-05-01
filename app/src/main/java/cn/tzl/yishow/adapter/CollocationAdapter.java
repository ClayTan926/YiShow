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

public class CollocationAdapter extends RecyclerView.Adapter<CollocationAdapter.ViewHolder> {

    private List<Integer> imageList;
    private List<String> textList;
    private List<String> numList;
    public CollocationAdapter(List<Integer> imageList,List<String> textList,List<String> numList) {
       this.imageList=imageList;
       this.textList=textList;
       this.numList=numList;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView clothesImage;
        TextView clothesText;
        TextView clothesNo;
        private ViewHolder(View itemView) {
            super(itemView);
            clothesImage=itemView.findViewById(R.id.iv_collocationImage);
            clothesText=itemView.findViewById(R.id.tv_collocationText);
            clothesNo=itemView.findViewById(R.id.tv_collocationNo);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_collocation,parent,false);
        return new CollocationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollocationAdapter.ViewHolder holder, int position) {
        holder.clothesImage.setImageResource(imageList.get(position));
        holder.clothesText.setText(textList.get(position));
        holder.clothesNo.setText(numList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


}
