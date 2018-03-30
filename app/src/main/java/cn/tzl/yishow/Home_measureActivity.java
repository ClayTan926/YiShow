package cn.tzl.yishow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zkk.view.rulerview.RulerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tanzl on 2018/2/23.
 * Class Comment:
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
    Button btnMeasureNext;
    private Boolean sex = true;
    private String gender;
    private String weight;
    private String height;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

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

    @OnClick({R.id.btn_measure_next, R.id.btn_register_info_sex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_measure_next:
                Intent intent = new Intent(Home_measureActivity.this, ShowDataActivity.class);
                intent.putExtra("gender", gender);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                startActivity(intent);
                break;
            case R.id.btn_register_info_sex:
                gender = "男";
                if (!sex) {
                    view.setBackgroundResource(R.drawable.user_sex_switch_boy);
                    sex = true;
                    gender = "男";
                    Log.e("Measure", "boy");
                } else {
                    sex = false;
                    view.setBackgroundResource(R.drawable.user_sex_switch_girl);
                    gender = "女";
                    Log.e("Measure", "gril");
                }
                break;
        }
    }
}