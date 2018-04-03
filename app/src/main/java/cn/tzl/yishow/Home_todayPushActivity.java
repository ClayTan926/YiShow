package cn.tzl.yishow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
=======
import android.support.v4.app.FragmentPagerAdapter;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
<<<<<<< HEAD
import butterknife.ButterKnife;
import cn.tzl.yishow.adapter.FragAdapter;


=======
import cn.tzl.yishow.adapter.FragAdapter;

>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
/**
 * Created by Tanzl on 2018/2/23.
 * Class Comment:
 */

public class Home_todayPushActivity extends AppCompatActivity {

    @BindView(R.id.vp_todayPush)
    ViewPager viewPager;
    FragAdapter fragAdapter;
    PushOneFragment oneFrag;
    PushTwoFragment twoFrag;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaypush);
<<<<<<< HEAD
        ButterKnife.bind(this);
        oneFrag=new PushOneFragment();
        twoFrag=new PushTwoFragment();
        List<Fragment> list=new ArrayList<>();
        list.add(oneFrag);
        list.add(twoFrag);
=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
        initViewPager();

    }

    private void initViewPager(){
        oneFrag=new PushOneFragment();
        twoFrag=new PushTwoFragment();
        List<Fragment> list=new ArrayList<>();
        list.add(oneFrag);
        list.add(twoFrag);

        fragAdapter=new FragAdapter(getSupportFragmentManager(),list);
<<<<<<< HEAD

=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(0);
    }
}
