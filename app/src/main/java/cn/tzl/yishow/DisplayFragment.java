package cn.tzl.yishow;

import android.app.Fragment;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.adapter.DisplayAdapter;
import cn.tzl.yishow.bean.Comment;

/**
 * Created by Tanzl on 2017/12/14 0014.
 */

public class DisplayFragment extends Fragment {
    private static final String TAG = "DisplayFragment";
    @BindView(R.id.rv_display)
    LRecyclerView lRecyclerView;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    DisplayAdapter displayAdapter;
    List<Comment> dataList;
    @BindView(R.id.show_load)
    ImageView showLoad;
    @BindView(R.id.fab_showAdd)
    FloatingActionButton fabShowAdd;
    private View view;
    private BmobUser bmobUser;
    public static DisplayFragment newInstance(String param1) {
        DisplayFragment fragment = new DisplayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.frag_display, container, false);
        ButterKnife.bind(this, view);
        loadAnima(true);
        loadData();
         bmobUser= BmobUser.getCurrentUser();


        return view;
    }


    private void initView() {
        loadAnima(false);

        Collections.reverse(dataList);  //将list倒过来排序
        displayAdapter = new DisplayAdapter(dataList);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(displayAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

    }

    private void loadData() {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("type", "comment");
        query.setLimit(50);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (list != null) {
                    Log.e("sasd", "done: " + list.size());
                    dataList = list;
                    initView();
                    displayAdapter.notifyDataSetChanged();
                    lRecyclerViewAdapter.notifyDataSetChanged();

                } else {
                    Log.e("sasd", "done: " + e);
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.fab_showAdd)
    public void onViewClicked() {
        if (null != bmobUser) {
            AddComment();
            Intent intent=new Intent(view.getContext(),NewCommentActivity.class);
            startActivityForResult(intent,0);
        }else {
            Toast.makeText(getActivity(),"当前未登录，登陆后，使用该功能",Toast.LENGTH_SHORT).show();
        }
    }

    private void AddComment() {
        loadAnima(true);
    }

    private void loadAnima(Boolean isShow) {
        Glide.with(this)
                .load(R.drawable.loading)
                .into(showLoad);
        if (isShow) {
            showLoad.setVisibility(View.VISIBLE);
        } else {
            showLoad.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 0) {
                if (data != null) {
                    loadData();
                    initView();
                    Log.e(TAG, "onActivityResult: 获取返回结果");
                }
            }
            if (resultCode==1){
                Log.e(TAG, "onActivityResult: back" );
            }
        }
    }
}
