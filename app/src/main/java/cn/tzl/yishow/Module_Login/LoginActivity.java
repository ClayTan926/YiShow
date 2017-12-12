package cn.tzl.yishow.Module_Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.Module_Login.model.UserLogin;
import cn.tzl.yishow.R;

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
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private String Mobile,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
