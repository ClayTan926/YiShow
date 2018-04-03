package cn.tzl.yishow;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.base.BaseActivity;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    Toolbar mtoolbar;
    //数据源的集合
    private List<Fragment> list = new ArrayList<Fragment>();

    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private CollocationFragment mCollocationFragment;
    private PersonFragment mPersonFragment;
    private DisplayFragment mDisplayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNavigationBar();
    }


    private void initBottomNavigationBar(){
        //设置按钮模式  MODE_FIXED表示固定,MODE_SHIFTING表示转移,需要在添加BottomNavigationItem之前进行设置，否则设置无效
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, getApplicationContext().getString(R.string.tab_home)).setActiveColor(R.color.myblack))
                .addItem(new BottomNavigationItem(R.mipmap.category, getApplicationContext().getString(R.string.tab_category)).setActiveColor(R.color.myblack))
                .addItem(new BottomNavigationItem(R.mipmap.collocation, getApplicationContext().getString(R.string.tab_collocation)).setActiveColor(R.color.myblack))
                .addItem(new BottomNavigationItem(R.mipmap.display, getApplicationContext().getString(R.string.tab_display)).setActiveColor(R.color.myblack))
                .addItem(new BottomNavigationItem(R.mipmap.person, getApplicationContext().getString(R.string.tab_person)).setActiveColor(R.color.myblack))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();

        bottomNavigationBar.setTabSelectedListener(MainActivity.this);
        setDefaultFragment();
    }

    /**
     * 设置默认的Fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = HomeFragment.newInstance("位置");
        transaction.replace(R.id.tb, mHomeFragment);
        transaction.commit();
    }

    private void initActionBar(int position){
        mtoolbar=findViewById(R.id.toolbar);
        ToolbarHelper.addMiddleTitle(getApplicationContext(),"分类",mtoolbar);
        String actionBarTitle=null;
        switch (position){
            case 1:
                actionBarTitle="分类";
                break;
            case 2:
                actionBarTitle="推荐搭配";
                break;
            case 3:
                actionBarTitle="秀场";
                break;
        }
        Log.e(TAG, "initActionBar: "+mtoolbar.getResources());
        ToolbarHelper.addMiddleTitle(getApplicationContext(),actionBarTitle,mtoolbar);
        setSupportActionBar(mtoolbar);
    }

    /**
     * Tab点击处理
     **/
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(getApplicationContext().getString(R.string.tab_home));
                }
                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mCategoryFragment == null) {
                    mCategoryFragment = CategoryFragment.newInstance(getApplicationContext().getString(R.string.tab_category));
                }
                transaction.replace(R.id.tb, mCategoryFragment);
                break;
            case 2:
                if (mCollocationFragment == null) {
                    mCollocationFragment = CollocationFragment.newInstance(getApplicationContext().getString(R.string.tab_collocation));
                }
                transaction.replace(R.id.tb, mCollocationFragment);
                break;
            case 3:
                if (mDisplayFragment == null) {
                    mDisplayFragment = DisplayFragment.newInstance(getApplicationContext().getString(R.string.tab_display));
                }
                transaction.replace(R.id.tb, mDisplayFragment);
                break;
            case 4:
                if (mPersonFragment == null) {
                    mPersonFragment = PersonFragment.newInstance(getApplicationContext().getString(R.string.tab_person));
                }
                transaction.replace(R.id.tb, mPersonFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }


    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {
        Log.d(TAG, "onTabReselected() called with: " + "position = [" + position + "]");
    }


}


