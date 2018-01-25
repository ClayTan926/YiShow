package cn.tzl.yishow;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.tzl.yishow.view.AvatarImageView;



/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class PersonFragment extends Fragment {
    AvatarImageView avatarImageView;
    public static PersonFragment newInstance(String param1) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public PersonFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        Bundle bundle = getArguments();
        avatarImageView=(AvatarImageView)view.findViewById(R.id.headportrait);

        avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Toast.makeText(view.getContext(),"设置新的头像成功",Toast.LENGTH_SHORT).show();
            }
        });
       /* String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_person);
        tv.setText(agrs1);*/
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mytag","aaaa");
        //在拍照、选取照片、裁剪Activity结束后，调用的方法
        if(avatarImageView != null){
            avatarImageView.onActivityResult(requestCode,resultCode,data);
        }
    }

}
