package cn.tzl.yishow.module_Ar;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.easyar.Engine;
import cn.tzl.yishow.R;

public class ARActivity extends AppCompatActivity {

    private static final String TAG = "ARActivity";
    //EasyAR AppKey
    private static final String key = "LL7elIxHFblHvmZa9We6zSEB1vzClVHtpOLHU4pgBzKpRFcAKlOOWsFBI2lOHuqExX000s7dKixZGFtFrxE13UEQGJ77xUWHfyQBCD0FrTG3LB9EePZeRv778sX6LQ9omCz0LWkILXN8QMPABwEnzmJq1mgOa7NdNze164k8nJ35ZE1UOgzcTqxQYKNAn14d2GRxJFg3";
    /*   @BindView(R.id.preview)
       FrameLayout preview;
       @BindView(R.id.screenshots)
       Button screenshots;
       private GLView glView;*/
    private SurfaceView sv_main_surface;
    private Camera camera;
    ImageView preImg;

    //沉浸式状态栏
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_camera);
        //ButterKnife.bind(this);

        sv_main_surface = findViewById(R.id.sv_main_surface);
        preImg = findViewById(R.id.img_preShow);
        sv_main_surface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto(v);
            }
        });
        //添加surface回调函数
        sv_main_surface.getHolder().addCallback(new SurfaceHolder.Callback() {


            @Override//控件创建时，打开照相机
            public void surfaceCreated(SurfaceHolder holder) {
                //打开照相机
                camera = Camera.open();
                //int cid=camera.;
                camera.setDisplayOrientation(90);//相机位置倒置问题，将相机回转90度，回复正常

                //设置参数
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
                parameters.setPictureFormat(PixelFormat.JPEG);
                parameters.setRotation(90); //照片旋转90度
                parameters.set("jpeg-quality", 100);
                camera.setParameters(parameters);
                camera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
                //将画面展示到SurfaceView
                try {
                    camera.setPreviewDisplay(sv_main_surface.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //开启预览效果
                camera.startPreview();

            }

            @Override//控件改变
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override//控件销毁
            public void surfaceDestroyed(SurfaceHolder holder) {
                //照相同一时刻只能允许一个软件打开
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();//释放内存
                    camera = null;
                }
            }
        });


    }


    public void takePhoto(final View view) {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                //技术：图片压缩技术（如果图片不压缩，图片大小会过大，会报一个oom内存溢出的错误）
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


                try {
                    String filePath = Environment.getExternalStorageDirectory() + "/DCIM/img_" + System.currentTimeMillis() + ".png";
                    //Log.e(TAG, "onPictureTaken: "+filePath );
                    FileOutputStream fos = new FileOutputStream(filePath);//图片保存路径

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);//压缩格式，质量，压缩路径
                    preImg.setImageBitmap(bitmap);
                    preImg.setVisibility(View.VISIBLE);
                    camera.stopPreview();//关闭预览 处理数据
                    camera.startPreview();//数据处理完后继续开始预览
                    //Toast.makeText(view.getContext(),"已保存",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



       /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
        }

        glView = new GLView(this);

        requestCameraPermission(new PermissionCallback() {
            @Override
            public void onSuccess() {
                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            @Override
            public void onFailure() {
            }
        });
    }


    //截图方法
    private void Screenshots(){
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        ImageView i=findViewById(R.id.show_sh);
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            ContentValues contentValues = new ContentValues(2);
           // String filePath = CommonUtils.getOutputMediaFile().getPath();//要保存照片的绝对路径

            //contentValues.put(MediaStore.Images.Media.DATA, filePath);
            //如果想拍完存在系统相机的默认目录,改为
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "111111.jpg");

            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri mPhotoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
            startActivityForResult(intent, 111);
        }


        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";
                Log.e("a7888", "Screenshots: filePath:"+filePath );
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                i.setImageBitmap(bm);
                os.flush();
                os.close();
                Log.d("a7888", "存储完成");
            } catch (Exception e) {
            }
        }
    }

    @OnClick({R.id.preview, R.id.screenshots})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.preview:
                Toast.makeText(this,"onclick",Toast.LENGTH_SHORT).show();
                break;
            case R.id.screenshots:
                Screenshots();
                Toast.makeText(this,"成功保存",Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private interface PermissionCallback {
        void onSuccess();

        void onFailure();
    }

    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;

    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glView != null) {
            glView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (glView != null) {
            glView.onPause();
        }
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/