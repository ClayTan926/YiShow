package cn.tzl.yishow;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.base.BaseActivity;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
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
        Log.e(TAG, "initView: welcome" );
        hintTime.setText(now.split(" ")[0]);
        ImageView imageView = (ImageView) findViewById(R.id.iv_welcome);
       /* RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);*/
        Glide.with(this)
                .load(R.drawable.load)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);

        //actionStart();
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
