package cn.tzl.yishow.module_Login.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.yishow.MainActivity;


public class UserLogin {
    private static final String TAG = "UserLogin";
    /**
     * @author tzl
     * @param context 上下文
     * @param Mobile 手机号
     * @param Password 密码
     */
    public static void userLogin(final Context context, String Mobile, final String Password ){
        BmobUser loginUser=new BmobUser();
        loginUser.setUsername(Mobile);
        loginUser.setPassword(Password);
        loginUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(context,"登录成功:",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    Log.e(TAG, "BmobException: "+e );
                }
            }
        });

      /*  BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        //查询是否有对应手机号
        query.addWhereEqualTo("mobile",Mobile);
        //返回一条数据
        query.setLimit(1);
        //执行查询方法
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> object, BmobException e) {
                if(e==null){
                    for (MyUser user : object) {
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
        });*/

    }
}
