package cn.tzl.yishow.module_Login.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.tzl.yishow.R;
import cn.tzl.yishow.module_Login.model.UserLogin;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_mobile)
    EditText et_Moble;
    @BindView(R.id.login_password)
    EditText et_Password;
    @BindView(R.id.btn_login)
    Button btn_Login;
    @BindView(R.id.tv_forget)
    TextView tv_Forget;
    @BindView(R.id.tv_register)
    TextView tv_Register;
   /* @BindView(R.id.fab)
    FloatingActionButton fab;*/
    private String Mobile,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_login);
        //初始化Bmob
        Bmob.initialize(this, "eeeae81bed48e80d6181bdf350980c64");
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Mobile=this.getIntent().getStringExtra("username");
        if (Mobile!=null&&"".equals(Mobile)){
            et_Moble.setText(Mobile);
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_forget, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Mobile=et_Moble.getText().toString();
                Password=et_Password.getText().toString();
                UserLogin.userLogin(LoginActivity.this,Mobile,Password);
                break;
            case R.id.tv_forget:
                Toast.makeText(this,"请联系管理员",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_register:
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }
    private void TransparentActionBar(){
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
