package cn.tzl.yishow;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;



import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.adapter.ARClothesAdapter;
import cn.tzl.yishow.adapter.CollocationAdapter;
import cn.tzl.yishow.adapter.FragAdapter;
import cn.tzl.yishow.module_Ar.ARActivity;
import cn.tzl.yishow.module_Ar.fragment.ClothesFragment;
import cn.tzl.yishow.module_Push.PushOneFragment;
import cn.tzl.yishow.module_Push.PushTwoFragment;

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
    private FragAdapter fragAdapter;
    private View view;

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
        view = inflater.inflate(R.layout.frag_clothesbar, container, false);
        ButterKnife.bind(this, view);
        initRecyView();
        return view;
    }


    private void initRecyView(){
        final GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        rvClothesBar.setLayoutManager(layoutManager);
        List<Integer> list=new ArrayList<>();
        list.add(R.drawable.shirt);
        list.add(R.drawable.plant3);
        ARClothesAdapter arClothesAdapter=new ARClothesAdapter(view.getContext(),list);
        rvClothesBar.setAdapter(arClothesAdapter);
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
                Intent intent = new Intent(getActivity(), ARActivity.class);
                startActivity(intent);

                break;
        }
    }
}
