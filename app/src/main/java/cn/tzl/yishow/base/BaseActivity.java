package cn.tzl.yishow.base;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;


import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.tzl.yishow.utils.ActivityCollector;
import cn.tzl.yishow.view.AvatarImageView;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private long exitTime = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        //初始化Bmob
        getPermission();
        Bmob.initialize(this, "eeeae81bed48e80d6181bdf350980c64");
        TransparentActionBar();
    }

    private void TransparentActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    //再按一次返回键退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                onDestroy();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void getPermission() {
        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(
                Manifest.permission.WRITE_SETTINGS
                , Manifest.permission.CAMERA
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_PHONE_STATE
               // , Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
                )
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        //授权成功时的操作
                        Log.e(TAG, "onGranted: 获取到权限");
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(BaseActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onDenied: fail");
                    }
                });

    }
}
