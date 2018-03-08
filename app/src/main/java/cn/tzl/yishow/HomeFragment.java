package cn.tzl.yishow;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.Adapter.HomeAdapter;
import cn.tzl.yishow.Utils.GlideImageLoader;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{


    @BindView(R.id.home_recyclerview)
    LRecyclerView homeRecyclerview;

    //@BindView(R.id.home_banner)
    Banner banner;
    //@BindView(R.id.btn_home_measure)
    Button btn_measure;
    //@BindView(R.id.btn_home_todayPush)
    Button btn_todyPush;
    //@BindView(R.id.btn_home_show)
    Button btn_show;
    //@BindView(R.id.btn_home_hot)
    Button btn_hot;

    HomeAdapter homeAdapter;
    LRecyclerViewAdapter mLRecyclerViewAdapter;

    private static final String TAG = "HomeFragment";
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
        View headerView=inflater.inflate(R.layout.header_home,container,false);
        ButterKnife.bind(this,view);
        //ButterKnife.bind(view.getContext(),headerView);

        initBanner(headerView);
        initRecyclerview(headerView);
        /*Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_home);
        tv.setText(agrs1);*/
        return view;
    }



    public void initBanner(View view){
        banner=view.findViewById(R.id.home_banner);
        btn_measure=view.findViewById(R.id.btn_home_measure);
        btn_todyPush=view.findViewById(R.id.btn_home_todayPush);
        btn_show=view.findViewById(R.id.btn_home_show);
        btn_hot=view.findViewById(R.id.btn_home_hot);

        List<Integer> images=new ArrayList<>();
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
}
