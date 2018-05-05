package cn.tzl.yishow;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.IRefreshHeader;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.adapter.HomeAdapter;
import cn.tzl.yishow.animation.ToolbarAnimation;
import cn.tzl.yishow.bean.Info;
import cn.tzl.yishow.module_Measure.Home_measureActivity;
import cn.tzl.yishow.utils.GlideImageLoader;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.home_recyclerview)
    LRecyclerView homeRecyclerview;
    @BindView(R.id.toolbar_home)
    Toolbar toolbar_home;
    @BindView(R.id.tv_toolbar)
    TextView tv_toolbar;
    @BindView(R.id.layout_appBar)
    AppBarLayout mAppBarLayout;

    //@BindView(R.id.home_banner)
    Banner banner;
    //@BindView(R.id.btn_home_measure)
    TextView btn_measure;
    //@BindView(R.id.btn_home_todayPush)
    TextView btn_todyPush;

    //@BindView(R.id.btn_home_show)
    Button btn_show;
    //@BindView(R.id.btn_home_hot)
    Button btn_hot;

    HomeAdapter homeAdapter;
    LRecyclerViewAdapter mLRecyclerViewAdapter;

    private static final String TAG = "HomeFragment";
    private static boolean isScroll = false;
    @BindView(R.id.main_load)
    ImageView mainLoad;
    private View headerView;
    private List<Info> dataList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.frag_home, container, false);
        headerView = inflater.inflate(R.layout.header_home, container, false);
        ButterKnife.bind(this, view);
        initBanner(headerView);
        loadAnima(true);
        loadInfo();
        initRecyclerview(headerView);
       /* LoadDataThread thread=new LoadDataThread();
        thread.run();*/
        return view;
    }


    public void initBanner(View view) {
        banner = view.findViewById(R.id.home_banner);
        btn_measure = view.findViewById(R.id.btn_home_measure);
        btn_todyPush = view.findViewById(R.id.btn_home_todayPush);
        btn_measure.setOnClickListener(this);
        btn_todyPush.setOnClickListener(this);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.b1);
        images.add(R.drawable.b2);
        images.add(R.drawable.b3);
        images.add(R.drawable.b4);
        images.add(R.drawable.b5);

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
        banner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


    public void initRecyclerview(View headerView) {

        homeAdapter = new HomeAdapter(dataList,getActivity());
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(headerView.getContext(), LinearLayoutManager.VERTICAL, false);
        homeRecyclerview.setLayoutManager(layoutManager);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);

        homeRecyclerview.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
                if (distanceY > 300 && distanceY < 350) {
                    ToolbarAnimation.showToolbar(toolbar_home);
                } else if (distanceY >= 350) {
                    toolbar_home.setVisibility(View.VISIBLE);
                } else {
                    ToolbarAnimation.hideToolbar(toolbar_home);

                }

            }

            @Override
            public void onScrollStateChanged(int state) {

            }
        });
        mLRecyclerViewAdapter.addHeaderView(headerView);
        // mLRecyclerViewAdapter.addFooterView(foot);
        homeRecyclerview.setPullRefreshEnabled(false);
        homeRecyclerview.setAdapter(mLRecyclerViewAdapter);
        homeRecyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"刷新中",Toast.LENGTH_SHORT).show();
                homeRecyclerview.isOnTop();
            }
        });


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
        switch (view.getId()) {
            case R.id.btn_home_measure:
                Intent measureIntent = new Intent(view.getContext(), Home_measureActivity.class);
                startActivity(measureIntent);
                break;
            case R.id.btn_home_todayPush:
                Intent todayPushIntent = new Intent(view.getContext(), Home_todayPushActivity.class);
                startActivity(todayPushIntent);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void loadInfo() {
        BmobQuery<Info> query = new BmobQuery<>();
        query.addWhereEqualTo("type", "info");
        query.setLimit(50);
        query.findObjects(new FindListener<Info>() {
            @Override
            public void done(List<Info> list, BmobException e) {
                if (list != null) {
                    loadAnima(false);
                    dataList = list;
                    homeAdapter.notifyDataSetChanged();
                    mLRecyclerViewAdapter.notifyDataSetChanged();
                    initRecyclerview(headerView);
                }
            }
        });

    }

    private void loadAnima(Boolean isShow) {
        Glide.with(this)
                .load(R.drawable.loading)
                .into(mainLoad);
        if (isShow) {
            mainLoad.setVisibility(View.VISIBLE);
        } else {
            mainLoad.setVisibility(View.GONE);
        }
    }


}
