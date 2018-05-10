package cn.tzl.yishow.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tzl.yishow.R;
import cn.tzl.yishow.bean.Comment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tanzl on 2018/2/8.
 * Class CommentBean:
 */

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private List<Comment> dataList = new ArrayList<>();
    private View view;
    private Map<Integer, Boolean> isLike = new HashMap<>();

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
        ImageView img_like;
        LinearLayout ll_comment, ll_like;

        private ViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.iv_displayAvater);
            userName = view.findViewById(R.id.tv_displayUserName);
            postTime = view.findViewById(R.id.tv_displayPostTime);
            content = view.findViewById(R.id.tv_displayContent);
            photo = view.findViewById(R.id.iv_displayPhoto);
            comment = view.findViewById(R.id.tv_displayComment);
            like = view.findViewById(R.id.tv_displayLike);
            img_like = view.findViewById(R.id.icon_displayLike);
            ll_comment = view.findViewById(R.id.ll_comment);
            ll_like = view.findViewById(R.id.ll_like);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_display, parent, false);
        return new DisplayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Comment comment = dataList.get(position);
        isLike.put(position, false);
        if (comment.getAvatar() != null && !comment.getAvatar().equals("")) {
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
        if (comment.getImage() != null && !comment.getImage().equals("")) {
            Glide.with(view)
                    .load(comment.getImage())
                    .into(holder.photo);
            holder.photo.setVisibility(View.VISIBLE);
        } else {
            holder.photo.setVisibility(View.GONE);
        }

        holder.comment.setText(comment.getCnum());
        holder.like.setText(comment.getLikenum());

        holder.ll_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLikeStatus(position, holder.img_like, holder.like, comment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    private void checkLikeStatus(int position, View imgView, TextView textView, Comment comment) {
        if (isLike.get(position)) {
            imgView.setBackgroundResource(R.drawable.like_black);
            textView.setText(String.valueOf(Integer.parseInt(comment.getLikenum())));
            isLike.put(position, false);
        } else {
            imgView.setBackgroundResource(R.drawable.like_red);
            textView.setText(String.valueOf(Integer.parseInt(comment.getLikenum()) + 1));
            isLike.put(position, true);
        }
    }

}
