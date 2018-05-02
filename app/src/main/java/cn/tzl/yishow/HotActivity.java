package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.adapter.CollocationAdapter;

/**
 * Created by Netted on 2018/4/3.
 */

public class HotActivity extends AppCompatActivity {

    @BindView(R.id.rv_collocation)
    RecyclerView collocationReView;
    CollocationAdapter collocationAdapter;
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

        imagelist.add(R.mipmap.cloths);
        imagelist.add(R.mipmap.home);
        imagelist.add(R.mipmap.category);

        textlist.add("当即热门男装1");
        textlist.add("当即热门男装2");
        textlist.add("当即热门男装3");
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
}
