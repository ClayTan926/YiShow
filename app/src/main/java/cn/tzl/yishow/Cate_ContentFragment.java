package cn.tzl.yishow;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.Adapter.CategoryContentAdapter;


/**
 * Created by Tanzl on 2018/2/6.
 * Class Comment:
 */

public class Cate_ContentFragment extends Fragment{
    public static final String TAG = "MyFragment";
    private String str;
    private int fragmentPostion;
    private List<String> list;

    @BindView(R.id.tv_cate_title)
    TextView tv_title;
    @BindView(R.id.rv_categorycontent)
    RecyclerView cateContentReView;

    LRecyclerViewAdapter mLRecyclerViewAdapter;
    CategoryContentAdapter contentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_content, container, false);
        ButterKnife.bind(this,view);
        //得到数据
        str = getArguments().getString(TAG);
        StringBuilder data=new StringBuilder();
        data.append("----- ").append(str).append(" -----");
        fragmentPostion=getArguments().getInt("fragmentposion");
        tv_title.setText(data.toString());
        initRecyclerView();
        return view;
    }

    private void initData(int postion){
        list=new ArrayList<>();
        switch (postion){
            case 0:
                list.clear();
                list.add("棉衣");
                list.add("夹克");
                list.add("T-Shirt");
                list.add("卫衣");
                list.add("毛衣");
                list.add("羽绒服");
                list.add("外套");
                list.add("风衣");
                list.add("衬衫");
                break;
            case 1:
                list.clear();
                list.add("牛仔裤");
                list.add("休闲裤");
                list.add("西裤");
                list.add("运动裤");
                list.add("哈伦裤");
                break;
            case 2:
                list.clear();
                list.add("板鞋");
                list.add("运动鞋");
                list.add("帆布鞋");
                list.add("休闲鞋");
                list.add("男靴");
                list.add("皮鞋");
                break;
            case 3:
                list.clear();
                list.add("双肩包");
                list.add("单肩包");
                list.add("手提包");
                list.add("公文包");
                list.add("旅行背包");
                break;
            case 4:
                list.clear();
                list.add("帽子");
                list.add("墨镜");
                list.add("腰带");
                list.add("手链");
                list.add("手表");
                list.add("领带");
                list.add("饰物");
                break;
        }

    }

    private void initRecyclerView(){
        initData(fragmentPostion);
        contentAdapter=new CategoryContentAdapter(list);
        cateContentReView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        //mLRecyclerViewAdapter = new LRecyclerViewAdapter(contentAdapter);
        // mLRecyclerViewAdapter.addHeaderView(head);
        // mLRecyclerViewAdapter.addFooterView(foot);

        cateContentReView.setAdapter(contentAdapter);
    }



}
