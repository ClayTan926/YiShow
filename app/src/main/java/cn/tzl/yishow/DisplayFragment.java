package cn.tzl.yishow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.adapter.DisplayAdapter;
import cn.tzl.yishow.bean.Comment;

/**
 * Created by Tanzl on 2017/12/14 0014.
 */

public class DisplayFragment extends Fragment {

    @BindView(R.id.rv_display)
    LRecyclerView lRecyclerView;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    DisplayAdapter displayAdapter;
    List<Comment> dataList;
    List<String> userList;
    List<String> timeList;
    List<String> contentList;
    List<Integer> photoList;
    List<String> commentList;
    List<String> likeList;
    @BindView(R.id.show_load)
    ImageView showLoad;
    @BindView(R.id.fab_showAdd)
    FloatingActionButton fabShowAdd;

    public static DisplayFragment newInstance(String param1) {
        DisplayFragment fragment = new DisplayFragment();
       /* Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_display, container, false);
        ButterKnife.bind(this, view);
        showLoad.setVisibility(View.VISIBLE);
        loadData();

        return view;
    }


    private void initView() {
        showLoad.setVisibility(View.GONE);
        displayAdapter = new DisplayAdapter(dataList);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

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
        AddComment();
    }

    private void AddComment(){
        showLoad.setVisibility(View.VISIBLE);

    }
}
