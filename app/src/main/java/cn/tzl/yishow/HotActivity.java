package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.adapter.CollocationAdapter;

/**
 * Created by Netted on 2018/4/3.
 */

public class HotActivity extends AppCompatActivity {

    @BindView(R.id.rv_collocation)
    RecyclerView collocationReView;
    CollocationAdapter collocationAdapter;
    @BindView(R.id.hot_back)
    ImageView hotBack;
    private List<Integer> imagelist;
    private List<String> textlist;
    private List<String> numlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.frag_collocation);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imagelist = new ArrayList<>();
        textlist = new ArrayList<>();
        numlist = new ArrayList<>();

        imagelist.add(R.drawable.shirt);
        imagelist.add(R.drawable.plant);
        imagelist.add(R.drawable.shoe2);

        textlist.add("当季热门1");
        textlist.add("当季热门2");
        textlist.add("当季热门3");
        for (int i = 0; i < imagelist.size(); i++) {
            getRandomnum(numlist);
        }
        collocationAdapter = new CollocationAdapter(imagelist, textlist, numlist);
        collocationReView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        collocationReView.setAdapter(collocationAdapter);
    }

    private void getRandomnum(List<String> list) {
        int num = (int) (Math.random() * 1000);
        list.add("" + num);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imagelist.clear();
        textlist.clear();
        numlist.clear();
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

    @OnClick(R.id.hot_back)
    public void onViewClicked() {
        finish();
    }
}
