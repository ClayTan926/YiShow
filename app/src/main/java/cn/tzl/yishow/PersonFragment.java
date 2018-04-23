package cn.tzl.yishow;


import android.app.Fragment;
import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.tzl.yishow.module_Login.activity.LoginActivity;
import cn.tzl.yishow.utils.ActivityCollector;
import cn.tzl.yishow.view.AvatarImageView;


/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class PersonFragment extends Fragment {

    private static final String TAG = "PersonFragment";
    private BmobUser bmobUser;
    @BindView(R.id.headportrait)
    AvatarImageView avatarImageView;
   /* @BindView(R.id.image)
    ImageView image;*/

/*    @BindView(R.id.headportrait)
    AvatarImageView avatarImageView;*/

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
        final View view = inflater.inflate(R.layout.frag_person, container, false);
        ButterKnife.bind(this, view);
        avatarImageView.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo) {
                Log.e(TAG, "设置成功");
                Toast.makeText(view.getContext(), "设置新的头像成功", Toast.LENGTH_SHORT).show();
            }
        });
        bmobUser = BmobUser.getCurrentUser();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bmobUser != null) {
            tvOnclickLogin.setText(bmobUser.getUsername());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "在拍照、选取照片、裁剪Activity结束后，调用的方法");

        //在拍照、选取照片、裁剪Activity结束后，调用的方法
        if (avatarImageView != null) {
            avatarImageView.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_onclick_login, R.id.tv_myCollection, R.id.tv_helpAndFeedback, R.id.tv_setting, R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_onclick_login:
                if (bmobUser == null) {
                    Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
                    view.getContext().startActivity(loginIntent);
                }
                break;
            case R.id.tv_myCollection:
                Intent userIntent = new Intent(view.getContext(), UserInfoActivity.class);
                view.getContext().startActivity(userIntent);
                break;
            case R.id.tv_helpAndFeedback:
                Intent helpIntent = new Intent(view.getContext(), HelpActivity.class);
                view.getContext().startActivity(helpIntent);
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_quit:
                BmobUser.logOut();   //清除缓存用户对象
                Log.e(TAG, "onViewClicked:  用户注销");
                //BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                ActivityCollector.finishAll();
                Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(loginIntent);
                break;
        }
    }
}
