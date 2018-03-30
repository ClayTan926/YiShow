package cn.tzl.yishow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ShowDataActivity extends AppCompatActivity {

    private static final String TAG = "ShowDataActivity";
    private String gender;
    private String weight;
    private String height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        gender=this.getIntent().getStringExtra("gender");
        weight=this.getIntent().getStringExtra("weight");
        height=this.getIntent().getStringExtra("height");
        Toast.makeText(this,"这里修改一下提交Git",Toast.LENGTH_SHORT);
        Log.e(TAG, "onCreate: user gender:"+gender+" weight:"+weight+" height:"+height );

    }
}
