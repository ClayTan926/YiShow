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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zkk.view.rulerview.RulerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.tzl.yishow.R;
import cn.tzl.yishow.module_Login.activity.LoginActivity;

/**
 * Created by Tanzl on 2018/2/23.
 * Class CommentBean:
 */

public class Home_measureActivity extends AppCompatActivity {

    @BindView(R.id.ruler_height)
    RulerView rulerHeight;
    @BindView(R.id.btn_register_info_sex)
    Button btnRegisterInfoSex;
    @BindView(R.id.tv_register_info_height_value)
    TextView tvRegisterInfoHeightValue;
    @BindView(R.id.tv_register_info_weight_value)
    TextView tvRegisterInfoWeightValue;
    @BindView(R.id.ruler_weight)
    RulerView rulerWeight;
    @BindView(R.id.btn_measure_next)
    TextView tvMeasureNext;
    private Boolean sex = true;
    private String gender="男";
    private String weight="55";
    private String height="170";
    private SharedPreferences sp_bodyData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentActionBar();
        setContentView(R.layout.act_measure);
        ButterKnife.bind(this);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (null != bmobUser) {
            initView();
        } else {
            Toast.makeText(this, "未登录，请登陆后使用该功能", Toast.LENGTH_SHORT).show();
           /* Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent,0);*/
            finish();
        }
        checkData();
    }

    private void initView() {
        sp_bodyData = getSharedPreferences("BodyData", MODE_PRIVATE);
        rulerHeight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tvRegisterInfoHeightValue.setText(String.valueOf(value));
                height = String.valueOf(value);
            }
        });
        rulerWeight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tvRegisterInfoWeightValue.setText(String.valueOf(value));
                weight = String.valueOf(value);
            }
        });
        tvRegisterInfoHeightValue.setText(String.valueOf(165));//显示初始化身高值
        tvRegisterInfoWeightValue.setText(String.valueOf(55));//显示初始化体重值

        rulerHeight.setValue(165, 80, 250, 1);

        rulerWeight.setValue(55, 20, 200, 0.1f);
    }

    private void TransparentActionBar(){
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    @OnClick({R.id.btn_measure_next, R.id.btn_register_info_sex,R.id.iv_measure_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_measure_back:
                finish();
                break;
            case R.id.btn_measure_next:
                Intent intent = new Intent(Home_measureActivity.this, ShowDataActivity.class);
                intent.putExtra("gender", gender);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                saveData(gender, height, weight);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_register_info_sex:
                if (!sex) {
                    view.setBackgroundResource(R.drawable.user_sex_switch_boy);
                    sex = true;
                    gender = "男";
                    Log.e("Measure", "boy");
                } else {
                    sex = false;
                    view.setBackgroundResource(R.drawable.user_sex_switch_girl);
                    gender = "女";
                    Log.e("Measure", "girl");
                }
                break;
        }
    }

    private void saveData(String gender, String height, String weight) {

        sp_bodyData.edit().putString("gender", gender).apply();
        sp_bodyData.edit().putString("height", height).apply();
        sp_bodyData.edit().putString("weight", weight).apply();

    }

    private void checkData() {
        if (null != sp_bodyData) {
            if (!sp_bodyData.getString("height", "").equals("")) {
                Intent intent = new Intent(this, ShowDataActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
