package cn.tzl.yishow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.tzl.yishow.base.BaseActivity;
import cn.tzl.yishow.module_Login.model.UserRegister;
//import retrofit2.Retrofit;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_mobile)
    EditText et_Mobile;
    @BindView(R.id.register_password)
    EditText et_Password;
    @BindView(R.id.btn_register)
    Button btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * 沉浸式状态栏
         */
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
        //绑定activity
        ButterKnife.bind(this);
        //默认初始化
        Bmob.initialize(this, "eeeae81bed48e80d6181bdf350980c64");

    }


    @OnClick({R.id.register_mobile, R.id.register_password, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_mobile:
                break;
            case R.id.register_password:
                break;
            case R.id.btn_register:
                String mobile = et_Mobile.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                if (mobile.equals("")) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if(mobile.length()!=11) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号格式", Toast.LENGTH_SHORT).show();
                }else if (password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "密码不能少于六位", Toast.LENGTH_SHORT).show();
                } else{
                    //用户注册
                    UserRegister.userRegister(RegisterActivity.this,mobile,password);
                }
                break;
        }
    }




}
