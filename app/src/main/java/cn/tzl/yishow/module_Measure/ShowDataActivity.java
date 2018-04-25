package cn.tzl.yishow.module_Measure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tzl.yishow.R;

public class ShowDataActivity extends AppCompatActivity {

    private static final String TAG = "ShowDataActivity";
    @BindView(R.id.tv_show_weight)
    TextView tvShowWeight;
    @BindView(R.id.tv_show_height)
    TextView tvShowHeight;
    @BindView(R.id.tv_show_gender)
    TextView tvShowGender;
    @BindView(R.id.tv_show_BMI)
    TextView tvShowBMI;
    @BindView(R.id.tv_shape1)
    TextView tvShape1;
    @BindView(R.id.tv_shape2)
    TextView tvShape2;
    @BindView(R.id.tv_shape3)
    TextView tvShape3;
    @BindView(R.id.tv_shape4)
    TextView tvShape4;
    @BindView(R.id.iv_show_back)
    ImageView ivBack;
    private String gender;
    private String weight;
    private String height;
    private SharedPreferences sp_data;
    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_show_data);
        ButterKnife.bind(this);
        getData();

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

    private void getData() {
        sp_data = this.getSharedPreferences("BodyData", MODE_PRIVATE);
        gender = this.getIntent().getStringExtra("gender");
        weight = this.getIntent().getStringExtra("weight");
        height = this.getIntent().getStringExtra("height");
        if (gender == null || gender.equals("")) {
            gender = sp_data.getString("gender", "男");
            height = sp_data.getString("height", "165");
            weight = sp_data.getString("weight", "55");
        }
        Log.e(TAG, "onCreate: user gender:" + gender + " weight:" + weight + " height:" + height);

        tvShowWeight.setText(weight);
        tvShowHeight.setText(height);
        tvShowGender.setText(gender);
        tvShowBMI.setText(calculateBMI(Float.parseFloat(height), Float.parseFloat(weight)));
        switch (checkBMI(bmi)) {
            case 0:
                tvShape1.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                tvShape2.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                tvShape3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 3:
                tvShape4.setTextColor(getResources().getColor(R.color.black));
                break;

        }
    }

    private String calculateBMI(float height, float weight) {
        if (height != 0 && weight != 0) {
            bmi = weight / (height * height / 10000);
        }
        return String.format("%.1f", bmi);
    }

    private int checkBMI(float bmi) {
        if (bmi < 18.5) {
            return 0;
        } else if (bmi <= 23.9) {
            return 1;
        } else if (bmi <= 27) {
            return 2;
        }
        return 3;

    }

    @OnClick({R.id.change, R.id.iv_show_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_back:
                finish();
                break;
            case R.id.change:
                sp_data.edit().clear().apply();//清除保存的数据
                Intent intent = new Intent(this, Home_measureActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
