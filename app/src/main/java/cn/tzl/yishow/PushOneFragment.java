package cn.tzl.yishow;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tanzl on 2018/2/23.
<<<<<<< HEAD
 * Class Comment:推荐1
=======
 * Class Comment:
>>>>>>> f5c1411b0617134f9ead25e38708c2168ec5e141
 */

public class PushOneFragment extends Fragment {

    //@BindView()
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.item_rv_todaypush,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
