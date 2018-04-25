package cn.tzl.yishow;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    @BindView(R.id.textView)
    TextView textView;
    private Animation animation;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_welcome);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        loadTime();
        loadGlide();
        handler.sendEmptyMessageDelayed(0, 1000);

        //actionStart();
    }

    @OnClick(R.id.welcome_skip)
    public void onViewClicked() {
        actionStart();
    }


    private void loadTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        Log.e(TAG, "initView: welcome");
        hintTime.setText(now.split(" ")[0]);

    }

    private void loadGlide() {
        RequestOptions options = new RequestOptions()
                //.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.ic_launcher_background);
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
                .into(ivWelcome);
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

    private void actionStart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private int getCount() {
        count--;
       if (count == 0) {
          actionStart();
        }
        return count;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount()+"");
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }
        }
    };
}
