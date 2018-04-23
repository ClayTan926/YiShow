package cn.tzl.yishow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_skip)
    TextView welcomeSkip;
    @BindView(R.id.hint_welcome)
    TextView hintWelcome;
    @BindView(R.id.hint_time)
    TextView hintTime;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        hintTime.setText(now.split(" ")[0]);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        actionStart();
    }

    @OnClick(R.id.welcome_skip)
    public void onViewClicked() {
       actionStart();
    }

    private void actionStart(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
