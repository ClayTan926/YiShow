package cn.tzl.yishow;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.adapter.CategoryAdapter;

import static cn.tzl.yishow.adapter.CategoryAdapter.mPosition;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class CategoryFragment extends Fragment  {

    @BindView(R.id.lv_category)
    ListView lv_category;

    private List<String> list;
    private CategoryAdapter madapter;
    private Cate_ContentFragment myFragment;

    public static CategoryFragment newInstance(String param1) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this,view);
        initView(view.getContext());
        return view;
    }

    private void initView(final Context context){
        initData();
        madapter=new CategoryAdapter(context,list);
        lv_category.setAdapter(madapter);
        lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //拿到当前位置
                mPosition = position;
                //即使刷新adapter
                madapter.notifyDataSetChanged();
                //Toast.makeText(context,mPosition+"",Toast.LENGTH_SHORT).show();
                for (int i = 0; i < list.size(); i++) {
                   initFragment();
                }
            }
        });
      initFragment();
    }

    private void initData(){
        list=new ArrayList<>();
        list.add("上衣");
        list.add("裤子");
        list.add("鞋子");
        list.add("包");
        list.add("配饰");
    }

    private void initFragment(){
        //创建MyFragment对象
        myFragment = new Cate_ContentFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);
        //通过bundle传值给Cate_ContentFragment
        Bundle bundle = new Bundle();
        bundle.putString(Cate_ContentFragment.TAG,list.get(mPosition) );
        bundle.putInt("fragmentposion",mPosition);
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }


}
