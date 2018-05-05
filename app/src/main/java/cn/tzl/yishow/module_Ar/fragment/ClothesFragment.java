package cn.tzl.yishow.module_Ar.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.tzl.yishow.R;

/**
 * Created by Tanzl on 2018/5/4.
 * Class Comment:
 */

public class ClothesFragment extends Fragment {
    //@BindView()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.item_ar_clothes,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
