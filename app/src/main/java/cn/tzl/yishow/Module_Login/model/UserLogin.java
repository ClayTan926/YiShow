package cn.tzl.yishow.Module_Login.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.tzl.yishow.MainActivity;
import cn.tzl.yishow.Module_Login.Bean.User;

/**
 * Created by Administrator on 2017/11/25 0025.
 */

public class UserLogin {
    /**
     * @author tzl
     * @param context
     * @param Mobile
     * @param Password
     */
    public static void userLogin(final Context context, String Mobile, final String Password ){
        BmobQuery<User> query = new BmobQuery<User>();
        //查询是否有对应手机号
        query.addWhereEqualTo("mobile",Mobile);
        //返回一条数据
        query.setLimit(1);
        //执行查询方法
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if(e==null){
                    for (User user : object) {
                        //获取密码信息
                        if(Password.equals(user.getPassword())){
                            Toast.makeText(context,"登陆成功",Toast.LENGTH_SHORT).show();
                            Intent loginIntent=new Intent(context,MainActivity.class);
                            context.startActivity(loginIntent);
                            ((Activity)context).finish();
                        }else{
                            Toast.makeText(context,"登陆失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

    }
}
