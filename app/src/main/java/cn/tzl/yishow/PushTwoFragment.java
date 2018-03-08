package cn.tzl.yishow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Tanzl on 2018/2/23.
 * Class Comment:
 */

public class PushTwoFragment extends Fragment {

    //@BindView()
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.item_rv_todaypush,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
