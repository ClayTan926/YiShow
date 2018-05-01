package cn.tzl.yishow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private List<Comment> dataList=new ArrayList<>();
  /*  private List<String> userList;
    private List<String> timeList;
    private List<String> contentList;
    private List<Integer> photoList;
    private List<String> commentList;
    private List<String> likeList;
*/
    //public DisplayAdapter(List<Integer> avatarList, List<String> userList, List<String> timeList, List<String> contentList, List<Integer> photoList,List<String> commentList,List<String> likeList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_display, parent, false);
        return new DisplayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = dataList.get(position);
        //holder.avatar.setImageResource();
        holder.userName.setText(comment.getUsername());
        holder.postTime.setText(comment.getCreatedAt());
        holder.content.setText(comment.getCcontent());
        //holder.photo.setImageResource(photoList.get(position));
        holder.comment.setText(comment.getCnum());
        holder.like.setText(comment.getLikenum());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
