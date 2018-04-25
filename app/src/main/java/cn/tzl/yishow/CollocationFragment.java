package cn.tzl.yishow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.adapter.CollocationAdapter;
import cn.tzl.yishow.module_Ar.ARActivity;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class CollocationFragment extends Fragment {
    private static final String TAG = "CollocationFragment";
    /* @BindView(R.id.rv_collocation)
     RecyclerView collocationReView;*/
    CollocationAdapter collocationAdapter;
    @BindView(R.id.rv_clothesBar)
    RecyclerView rvClothesBar;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    public static CollocationFragment newInstance(String param1) {
        CollocationFragment fragment = new CollocationFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_clothesbar, container, false);
        ButterKnife.bind(this, view);
        //initView();
        return view;
    }


    private void initView() {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.rv_clothesBar, R.id.fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_clothesBar:
                break;
            case R.id.fab_add:
                //调用AR SDK
                Log.e(TAG, "onViewClicked: 跳转AR页面");
                Intent intent=new Intent(getActivity(), ARActivity.class);
                startActivity(intent);

                break;
        }
    }
}
