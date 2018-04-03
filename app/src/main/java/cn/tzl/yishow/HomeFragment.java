package cn.tzl.yishow;


<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
=======
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;

=======
import android.widget.Toast;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

<<<<<<< HEAD

=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.adapter.HomeAdapter;
<<<<<<< HEAD
import cn.tzl.yishow.animation.ToolbarAnimation;
=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
import cn.tzl.yishow.utils.GlideImageLoader;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

<<<<<<< HEAD
public class HomeFragment extends Fragment implements View.OnClickListener {
=======
public class HomeFragment extends Fragment implements View.OnClickListener{
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141


    @BindView(R.id.home_recyclerview)
    LRecyclerView homeRecyclerview;
<<<<<<< HEAD
    @BindView(R.id.toolbar_home)
    Toolbar toolbar_home;
    @BindView(R.id.tv_toolbar)
    TextView tv_toolbar;
    @BindView(R.id.layout_appBar)
    AppBarLayout mAppBarLayout;

=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141

    //@BindView(R.id.home_banner)
    Banner banner;
    //@BindView(R.id.btn_home_measure)
    Button btn_measure;
    //@BindView(R.id.btn_home_todayPush)
    Button btn_todyPush;
<<<<<<< HEAD

=======
    //@BindView(R.id.btn_home_show)
    Button btn_show;
    //@BindView(R.id.btn_home_hot)
    Button btn_hot;
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141

    HomeAdapter homeAdapter;
    LRecyclerViewAdapter mLRecyclerViewAdapter;

    private static final String TAG = "HomeFragment";
<<<<<<< HEAD
    private static boolean isScroll = false;


=======
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
<<<<<<< HEAD
        View headerView = inflater.inflate(R.layout.header_home, container, false);
        ButterKnife.bind(this, view);


        initBanner(headerView);
        initRecyclerview(headerView);

=======
        View headerView=inflater.inflate(R.layout.header_home,container,false);
        ButterKnife.bind(this,view);
        //ButterKnife.bind(view.getContext(),headerView);

        initBanner(headerView);
        initRecyclerview(headerView);
        /*Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_home);
        tv.setText(agrs1);*/
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
        return view;
    }


<<<<<<< HEAD
    public void initBanner(View view) {
        banner = view.findViewById(R.id.home_banner);
        btn_measure = view.findViewById(R.id.btn_home_measure);
        btn_todyPush = view.findViewById(R.id.btn_home_todayPush);

        btn_measure.setOnClickListener(this);
        btn_todyPush.setOnClickListener(this);

        List<Integer> images = new ArrayList<>();
=======

    public void initBanner(View view){
        banner=view.findViewById(R.id.home_banner);
        btn_measure=view.findViewById(R.id.btn_home_measure);
        btn_todyPush=view.findViewById(R.id.btn_home_todayPush);
        btn_show=view.findViewById(R.id.btn_home_show);
        btn_hot=view.findViewById(R.id.btn_home_hot);

        List<Integer> images=new ArrayList<>();
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
        images.add(R.mipmap.home);
        images.add(R.mipmap.category);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

<<<<<<< HEAD
    public void initRecyclerview(View headerView) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        homeAdapter = new HomeAdapter(list);
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        homeRecyclerview.setLayoutManager(layoutManager);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);
        homeRecyclerview.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onScrollUp() {
                Log.e(TAG, "onScrollUp: ");
            }

            @Override
            public void onScrollDown() {
                Log.e(TAG, "onScrollDown: ");
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
                if (distanceY > 300 && distanceY < 350) {
                    ToolbarAnimation.showToolbar(toolbar_home);
                } else if (distanceY >= 350) {
                    toolbar_home.setVisibility(View.VISIBLE);
                } else {
                    ToolbarAnimation.hideToolbar(toolbar_home);
                    //toolbar_home.setVisibility(View.GONE);
                }

                Log.e(TAG, "onScrolled: dx:" + distanceX + " dy:" + distanceY);
            }

            @Override
            public void onScrollStateChanged(int state) {

                Log.e(TAG, "onScrollStateChanged: " + state);
            }
        });
        mLRecyclerViewAdapter.addHeaderView(headerView);
        // mLRecyclerViewAdapter.addFooterView(foot);
        homeRecyclerview.setAdapter(mLRecyclerViewAdapter);

=======
    public void initRecyclerview(View headerView){

        List<String> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add(""+i);
        }
        homeAdapter=new HomeAdapter(list);
        homeRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);
        mLRecyclerViewAdapter.addHeaderView(headerView);
       // mLRecyclerViewAdapter.addFooterView(foot);

        homeRecyclerview.setAdapter(mLRecyclerViewAdapter);


>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
    }


    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }


    @Override
    public void onClick(View view) {
<<<<<<< HEAD
        switch (view.getId()) {
            case R.id.btn_home_measure:
                Log.e(TAG, "onClick: 按键1");
                Intent measureIntent = new Intent(view.getContext(), Home_measureActivity.class);
                startActivity(measureIntent);
                break;
            case R.id.btn_home_todayPush:
                Log.e(TAG, "onClick: 按键2");
                Intent todayPushIntent = new Intent(view.getContext(), Home_todayPushActivity.class);
                startActivity(todayPushIntent);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
=======
        switch (view.getId()){
            case R.id.btn_home_measure:
                Intent measureIntent=new Intent(view.getContext(),Home_mesureActivity.class);
                startActivity(measureIntent);
                break;
            case R.id.btn_home_todayPush:
                Intent todayPushIntent=new Intent(view.getContext(),Home_todayPushActivity.class);
                startActivity(todayPushIntent);
                break;
            case R.id.btn_home_show:
                break;
            case R.id.btn_home_hot:
                break;
            default:
                Log.d(TAG, "onClick: "+view.getId());
                break;
        }
    }
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
}
