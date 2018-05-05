package cn.tzl.yishow.module_Ar;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
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

import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
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
    @BindView(R.id.preview)
    FrameLayout preview;
    @BindView(R.id.screenshots)
    Button screenshots;
    private GLView glView;
    private SurfaceView sv_main_surface;
    private Camera camera;
    @BindView(R.id.show_sh)
    ImageView preImg;
    private Bitmap bitmap;
    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;
    //private ImageView mpreView = findViewById(R.id.show_sh);


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
        setContentView(R.layout.act_ar);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
        }

        glView = new GLView(getApplicationContext());

        requestCameraPermission(new PermissionCallback() {
            @Override
            public void onSuccess() {
                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            }

            @Override
            public void onFailure() {
            }
        });
        glView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ARActivity.this,"保存",Toast.LENGTH_SHORT).show();
             /*   String filePath = Environment.getExternalStorageDirectory() + "/DCIM/img_" + System.currentTimeMillis() + ".png";
                String fileName ="img_" + System.currentTimeMillis() + ".png";
                Bitmap bm = cropView(glView);
                if (bm != null) {
                    preImg.setImageBitmap(bm);
                    Log.e(TAG, "onClick: "+bm.toString() );
                }*/
                Log.e(TAG, "onClick:  null" );
                //savePhoto(bm);

            }
        });

    }

    /**
     * 开始考虑用剪切的方法，但是截取只适合静态界面，这里surfaceView是动态的（在不断重绘）不能剪切，后来考虑用绘图的方式将两个bitmap合在一起。
     *
     * @param view 截取的当前view
     * @return bitmap结果
     */
    private Bitmap cropView(View view) {
        Log.e(TAG, "cropView: "+view.toString() );
        view.measure(View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.UNSPECIFIED));
        Log.e(TAG, "cropView: "+view.getHeight()+"ss "+view.getWidth() );
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);

        Bitmap bitmap = view.getDrawingCache();
        if (bitmap!=null){
            Log.e(TAG, "cropView: bitmap"+bitmap.toString() );
        }else {
            Log.e(TAG, "cropView: sdasd" );
        }
        return bitmap;
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
                    // preImg.setImageBitmap(bitmap);
                    //preImg.setVisibility(View.VISIBLE);
                    camera.stopPreview();//关闭预览 处理数据
                    camera.startPreview();//数据处理完后继续开始预览
                    //Toast.makeText(view.getContext(),"已保存",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //截图方法
    private void Screenshots() {
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        /*//调用系统相机
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
        }*/

        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                //String filePath = sdCardPath + File.separator + "screenshot.png";
                String filePath = Environment.getExternalStorageDirectory() + "/DCIM/screenshot.png";
                Log.e("a7888", "Screenshots: filePath:" + filePath);
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                preImg.setImageBitmap(bm);
                os.flush();
                os.close();
                Log.d("a7888", "存储完成");
            } catch (Exception e) {
            }
        }
    }

    private void savePhoto(Bitmap bitmap){
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                //String filePath = sdCardPath + File.separator + "screenshot.png";
                String filePath = Environment.getExternalStorageDirectory() + "/DCIM/screenshot.png";
                Log.e("a7888", "Screenshots: filePath:" + filePath);
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                preImg.setImageBitmap(bm);
                os.flush();
                os.close();
                Log.d("a7888", "存储完成");
            } catch (Exception e) {
            }
        }
    }


    private void save(Bitmap bitmap, String filePath, String fileName) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // 创建文件夹
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos); // 向缓冲区之中压缩图片
            bos.flush();
            bos.close();
            Toast.makeText(getApplicationContext(),
                    "拍照成功，照片已保存在" + fileName + "文件之中！", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "拍照失败！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 合并两张bitmap为一张
     *
     * @param background 背景
     * @param foreground 前景
     * @return Bitmap
     */
    public static Bitmap combineBitmap(Bitmap background, Bitmap foreground) {
        if (background == null) {
            return null;
        }
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        Bitmap newMap = Bitmap
                .createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newMap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, (bgWidth - fgWidth) / 2,
                (bgHeight - fgHeight) / 2, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return newMap;
    }

    @OnClick({R.id.preview, R.id.screenshots})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.preview:
                Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show();
                //takePhoto(view);
                break;
            case R.id.screenshots:
              /*  //Screenshots();
                //cropView(glView);
                Button b=findViewById(R.id.screenshots);
                Bitmap bm = cropView(glView);
                if (bm != null) {
                    preImg.setImageBitmap(bm);
                }*/
                //b.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "成功保存", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w, h, config);
        // 注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private interface PermissionCallback {
        void onSuccess();

        void onFailure();
    }


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
