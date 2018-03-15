package cn.tzl.yishow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.tzl.yishow.R;

/**
 * Created by Tanzl on 2018/2/5.
 * Class Comment:
 */

public class CategoryAdapter extends BaseAdapter {

        private Context context;
        private List<String> list;
        public static int mPosition;

        public CategoryAdapter(Context context, List<String> list){
            this.context =context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {

            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder  viewHolder=new ViewHolder();
            if(view==null){
                view = LayoutInflater.from(context).inflate(R.layout.item_lv_category, null);
                viewHolder.tv= view.findViewById(R.id.item_tv_category);
                mPosition = position;
                viewHolder.tv.setText(list.get(position));
             /*   if (position == view.mPosition) {
                    view.setBackgroundResource(R.drawable.tongcheng_all_bg01);
                } else {
                    view.setBackgroundColor(Color.parseColor("#f4f4f4"));
                }*/
                view.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tv.setText(list.get(position));
          /*  if (position == MainActivity.mPosition) {
                view.setBackgroundResource(R.drawable.tongcheng_all_bg01);
            } else {
                view.setBackgroundColor(Color.parseColor("#f4f4f4"));
            }*/
            return view;
        }

        private static class ViewHolder
        {
            TextView tv;
        }
    }

