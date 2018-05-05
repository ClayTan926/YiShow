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
 * Class CommentBean:推荐1
 */

public class PushOneFragment extends Fragment {

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
        String text="\u3000\u3000这款简约又清新的男士T恤，在领口位置采用的舒适圆领设计，衣服前腹位置的日文印花" +
                "，加上后背位置的字母胶印，无疑是点睛之笔，充满了其品牌独特的设计理念。";
        tvDescription.setText(text);
        ivAllclothes.setImageResource(R.drawable.shirt);
        ivClothesone.setImageResource(R.drawable.shirt2);
        ivClothestwo.setImageResource(R.drawable.plant);
        ivClothesthree.setImageResource(R.drawable.shoe1);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
