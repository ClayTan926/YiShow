package cn.tzl.yishow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.tzl.yishow.R;
import cn.tzl.yishow.bean.Comment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tanzl on 2018/2/8.
 * Class CommentBean:
 */

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private List<Comment> dataList = new ArrayList<>();
    View view;
    public DisplayAdapter(List<Comment> list) {
        this.dataList = list;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView avatar;
        TextView userName;
        TextView postTime;
        TextView content;
        ImageView photo;
        TextView comment;
        TextView like;

        private ViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.iv_displayAvater);
            userName = view.findViewById(R.id.tv_displayUserName);
            postTime = view.findViewById(R.id.tv_displayPostTime);
            content = view.findViewById(R.id.tv_displayContent);
            photo = view.findViewById(R.id.iv_displayPhoto);
            comment = view.findViewById(R.id.tv_displayComment);
            like = view.findViewById(R.id.tv_displayLike);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_display, parent, false);
        return new DisplayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = dataList.get(position);
        if (comment.getAvatar() != null) {
            Glide.with(view)
                    .load(comment.getAvatar())
                    .into(holder.avatar);
        } else {
            holder.avatar.setImageResource(R.mipmap.avaterimage);
        }
        //holder.avatar.setImageResource();
        holder.userName.setText(comment.getUsername());
        holder.postTime.setText(comment.getCreatedAt());
        holder.content.setText(comment.getCcontent());
        if (comment.getImage() != null) {
            Glide.with(view)
                    .load(comment.getAvatar())
                    .into(holder.photo);
            holder.photo.setVisibility(View.VISIBLE);
        } else {
            holder.photo.setVisibility(View.GONE);
        }

        holder.comment.setText(comment.getCnum());
        holder.like.setText(comment.getLikenum());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
