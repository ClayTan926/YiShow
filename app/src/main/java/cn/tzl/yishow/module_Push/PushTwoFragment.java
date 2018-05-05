package cn.tzl.yishow.module_Push;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tzl.yishow.R;

/**
 * Created by Tanzl on 2018/2/23.
 * Class CommentBean:推荐2
 */

public class PushTwoFragment extends Fragment {

    @BindView(R.id.iv_allclothes)
    ImageView ivAllclothes;
    @BindView(R.id.iv_clothesone)
    ImageView ivClothesone;
    @BindView(R.id.iv_clothestwo)
    ImageView ivClothestwo;
    @BindView(R.id.iv_clothesthree)
    ImageView ivClothesthree;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    //@BindView()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_rv_todaypush, container, false);
        ButterKnife.bind(this, view);
        loadData();
        return view;
    }

    private void loadData(){
        String text="\u3000\u3000炎热的夏天，清凉又不失潮流气息的T恤是基础款搭配。宽松清爽的版型，配上彰显个性" +
                "的印花，显出独特的时髦范";
        tvDescription.setText(text);
        ivAllclothes.setImageResource(R.drawable.shirt3);
        ivClothesone.setImageResource(R.drawable.shirt1);
        ivClothestwo.setImageResource(R.drawable.plant3);
        ivClothesthree.setImageResource(R.drawable.shoe2);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
