package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.bean.MyUser;
import cn.tzl.yishow.view.CircleImageView;

public class UserInfoActivity extends AppCompatActivity {
    @BindView(R.id.iv_AvatarView)
    CircleImageView ivAvatarView;
    private BmobUser bmobUser;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_oldPwd)
    EditText etOldPwd;
    @BindView(R.id.et_newPwd)
    EditText etNewPwd;
    @BindView(R.id.et_confirmPwd)
    EditText etConfirmPwd;
    @BindView(R.id.btn_save_user_info)
    Button btnSaveUserInfo;
    @BindView(R.id.iv_user_back)
    ImageView ivUserBack;
    private String url;
    private MyUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        //初始化Bmob
        Bmob.initialize(this, "eeeae81bed48e80d6181bdf350980c64");
        setContentView(R.layout.act_user_info);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            getAvatar();
            etUsername.setText(bmobUser.getUsername());
            etMobile.setText(bmobUser.getMobilePhoneNumber());
            etMail.setText(bmobUser.getEmail());
            if (url!=null) {
                Glide.with(this)
                        .load(url)
                        .into(ivAvatarView);
            }
        }
    }

    private void TransparentActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private String getAvatar() {
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("username", bmobUser.getUsername());
        query.setLimit(1);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (list != null) {
                    Log.e("person", "done: " + list.size());
                    user = list.get(0);
                    if (user != null) {
                        if (user.getAvatar() != null || !user.getAvatar().equals("")) {
                            url = user.getAvatar();
                        }
                    }

                } else {
                    Log.e("person", "done: " + e);
                }
            }
        });
        return url;

    }

    @OnClick({R.id.btn_save_user_info, R.id.iv_user_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_back:
                finish();
                break;
            case R.id.btn_save_user_info:
                break;
        }
    }
}
