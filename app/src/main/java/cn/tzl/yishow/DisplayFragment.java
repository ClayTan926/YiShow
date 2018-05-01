package cn.tzl.yishow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.adapter.DisplayAdapter;
import cn.tzl.yishow.bean.Comment;
import cn.tzl.yishow.bean.Info;

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
       /* Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = view.findViewById(R.id.tv_display);
        tv.setText(agrs1);*/
        loadData();

        return view;
    }


    private void initView() {
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
                    Log.e("sasd", "done: "+list.size() );
                    dataList=list;
                    initView();
                    displayAdapter.notifyDataSetChanged();
                    lRecyclerViewAdapter.notifyDataSetChanged();

                }else {
                    Log.e("sasd", "done: "+e );
                }
            }
        });
        /*avatarList = new ArrayList<>();
        userList = new ArrayList<>();
        timeList = new ArrayList<>();
        contentList = new ArrayList<>();
        photoList = new ArrayList<>();
        commentList = new ArrayList<>();
        likeList = new ArrayList<>();
        final String content = "大佛核对是否考虑设立付款还是理科粉红色法律是客户分类";

        avatarList.add(R.mipmap.avaterimage);
        avatarList.add(R.mipmap.avaterimage);
        avatarList.add(R.mipmap.avaterimage);

        photoList.add(R.mipmap.cloths);
        photoList.add(R.mipmap.cloths);
        photoList.add(R.mipmap.cloths);

        userList.add("Jack");
        userList.add("Mike");
        userList.add("Rose");


        timeList.add(getWindowTime());
        timeList.add(getWindowTime());
        timeList.add(getWindowTime());

        contentList.add(content);
        contentList.add(content);
        contentList.add(content);

        commentList.add(getRandomNum());
        commentList.add(getRandomNum());
        commentList.add(getRandomNum());

        likeList.add(getRandomNum());
        likeList.add(getRandomNum());
        likeList.add(getRandomNum());*/
    }

    private String getWindowTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    private String getRandomNum() {
        int num = (int) (Math.random() * 10000);
        return String.valueOf(num);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       /* avatarList.clear();
        userList.clear();
        timeList.clear();
        contentList.clear();
        photoList.clear();
        commentList.clear();
        likeList.clear();*/
    }


}
