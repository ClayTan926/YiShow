package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class UserInfoActivity extends AppCompatActivity {
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

    private void loadData(){
        bmobUser=BmobUser.getCurrentUser();
        if (bmobUser!=null){
            etUsername.setText(bmobUser.getUsername());
            etMobile.setText(bmobUser.getMobilePhoneNumber());
            etMail.setText(bmobUser.getEmail());
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

    @OnClick(R.id.btn_save_user_info)
    public void onViewClicked() {
    }
}
