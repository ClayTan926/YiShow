package cn.tzl.yishow;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.adapter.FragAdapter;
import cn.tzl.yishow.module_Push.PushOneFragment;
import cn.tzl.yishow.module_Push.PushTwoFragment;


/**
 * Created by Tanzl on 2018/2/23.
 * Class CommentBean:
 */

public class Home_todayPushActivity extends AppCompatActivity {

    @BindView(R.id.vp_todayPush)
    ViewPager viewPager;
    FragAdapter fragAdapter;
    PushOneFragment oneFrag;
    PushTwoFragment twoFrag;
    @BindView(R.id.tv_todayPush_More)
    TextView tvTodayPushMore;
    @BindView(R.id.iv_todayPush_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_todaypush);
        ButterKnife.bind(this);
        oneFrag = new PushOneFragment();
        twoFrag = new PushTwoFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(oneFrag);
        list.add(twoFrag);

        initViewPager();

    }

    private void initViewPager() {
        oneFrag = new PushOneFragment();
        twoFrag = new PushTwoFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(oneFrag);
        list.add(twoFrag);

        fragAdapter = new FragAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.tv_todayPush_More,R.id.iv_todayPush_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_todayPush_More:
                Intent intent=new Intent("cn.tzl.yishow.more");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.iv_todayPush_back:
                finish();
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
