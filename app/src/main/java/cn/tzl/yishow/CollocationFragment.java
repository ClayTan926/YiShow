package cn.tzl.yishow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.adapter.CollocationAdapter;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class CollocationFragment extends Fragment {
    @BindView(R.id.rv_collocation)
    RecyclerView collocationReView;
    CollocationAdapter collocationAdapter;
    private List<Integer> imagelist;
    private List<String> textlist;
    private List<String> numlist;
    public static CollocationFragment newInstance(String param1) {
        CollocationFragment fragment = new CollocationFragment();
        /*Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);*/
        return fragment;
    }

    public CollocationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collocation, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }


    private void initView(){
        imagelist=new ArrayList<>();
        textlist=new ArrayList<>();
        numlist=new ArrayList<>();

        imagelist.add(R.mipmap.cloths);
        imagelist.add(R.mipmap.home);
        imagelist.add(R.mipmap.category);

        textlist.add("当即热门男装1");
        textlist.add("当即热门男装2");
        textlist.add("当即热门男装3");
        for (int i=0;i<imagelist.size();i++) {
            getRandomnum(numlist);
        }
        collocationAdapter=new CollocationAdapter(imagelist,textlist,numlist);
        collocationReView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        collocationReView.setAdapter(collocationAdapter);
    }

    private void getRandomnum(List<String> list){
        int num=(int)(Math.random()*1000);
        list.add(""+num);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imagelist.clear();
        textlist.clear();
        numlist.clear();
    }
}
