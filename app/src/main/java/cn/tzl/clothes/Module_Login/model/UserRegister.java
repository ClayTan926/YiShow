package cn.tzl.clothes.Module_Login.model;

import android.content.Context;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.clothes.Module_Login.Bean.User;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class UserRegister {

    public static void userRegister(final Context mcontext, String mobile, String password) {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword(password);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(mcontext, "注册成功", Toast.LENGTH_SHORT).show();
                } else if (e.getErrorCode() == 401) {
                    Toast.makeText(mcontext, "当前手机号码已被注册", Toast.LENGTH_SHORT).show();
                } else if (e.getErrorCode() == 9010) {
                    Toast.makeText(mcontext, "网络超时，请稍后重试。" + e.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mcontext, "注册失败，请稍后重试。" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}