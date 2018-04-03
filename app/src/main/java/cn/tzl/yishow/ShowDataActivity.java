package cn.tzl.yishow;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ShowDataActivity extends AppCompatActivity {

    private static final String TAG = "ShowDataActivity";
    private String gender;
    private String weight;
    private String height;
    private SharedPreferences sp_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        getData();

    }
    private void getData(){
        sp_data=this.getSharedPreferences("BodyData",MODE_PRIVATE);
        gender=this.getIntent().getStringExtra("gender");
        weight=this.getIntent().getStringExtra("weight");
        height=this.getIntent().getStringExtra("height");
        if (gender.equals("")&&gender!=null){
            gender=sp_data.getString("gender","男");
            height=sp_data.getString("height","165");
            weight=sp_data.getString("weight","55");
        }

        Log.e(TAG, "onCreate: user gender:"+gender+" weight:"+weight+" height:"+height );
    }
}
