package cn.tzl.yishow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class DisplayFragment extends Fragment {

    public static DisplayFragment newInstance(String param1) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public DisplayFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        TextView tv = (TextView)view.findViewById(R.id.tv_display);
        tv.setText(agrs1);
        return view;
    }



}
