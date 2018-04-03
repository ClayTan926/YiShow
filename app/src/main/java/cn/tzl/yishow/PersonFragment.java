package cn.tzl.yishow;


import android.app.Fragment;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
=======
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.utils.ActivityCollector;
<<<<<<< HEAD
import cn.tzl.yishow.view.AvatarImageView;
=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141


/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class PersonFragment extends Fragment {
<<<<<<< HEAD
    private static final String TAG = "PersonFragment";

    @BindView(R.id.headportrait)
    AvatarImageView avatarImageView;
   /* @BindView(R.id.image)
    ImageView image;*/
=======

/*    @BindView(R.id.headportrait)
    AvatarImageView avatarImageView;*/
    @BindView(R.id.image)
    ImageView image;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
    @BindView(R.id.tv_onclick_login)
    TextView tvOnclickLogin;
    @BindView(R.id.tv_myCollection)
    TextView tvMyCollection;
    @BindView(R.id.tv_helpAndFeedback)
    TextView tvHelpAndFeedback;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_quit)
    TextView tvQuit;

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
        ButterKnife.bind(this, view);
<<<<<<< HEAD
        avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Log.e(TAG, "设置成功");
                Toast.makeText(view.getContext(), "设置新的头像成功", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "在拍照、选取照片、裁剪Activity结束后，调用的方法");
=======
        Bundle bundle = getArguments();

       /* avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Toast.makeText(view.getContext(), "设置新的头像成功", Toast.LENGTH_SHORT).show();
            }
        });*/
       /* String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_person);
        tv.setText(agrs1);*/
        return view;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("mytag", "aaaa");
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
        //在拍照、选取照片、裁剪Activity结束后，调用的方法
        if (avatarImageView != null) {
            avatarImageView.onActivityResult(requestCode, resultCode, data);
        }
<<<<<<< HEAD
    }
=======
    }*/
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141

    @Override
    public void onDestroyView() {
        super.onDestroyView();
<<<<<<< HEAD
    }

    @OnClick({R.id.tv_onclick_login, R.id.tv_myCollection, R.id.tv_helpAndFeedback, R.id.tv_setting, R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
=======
        //null.unbind();
    }

    @OnClick({R.id.image, R.id.tv_onclick_login, R.id.tv_myCollection, R.id.tv_helpAndFeedback, R.id.tv_setting, R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                break;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
            case R.id.tv_onclick_login:
                break;
            case R.id.tv_myCollection:
                break;
            case R.id.tv_helpAndFeedback:
                Intent helpIntent=new Intent(view.getContext(),HelpActivity.class);
                view.getContext().startActivity(helpIntent);
                break;
            case R.id.tv_setting:

                break;
            case R.id.tv_quit:
                ActivityCollector.finishAll();
                break;
        }
    }
}
