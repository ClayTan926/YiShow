package cn.tzl.yishow;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


public class NoticeActivity extends AppCompatActivity {
    private Switch notice, vibrate, sound;
    private Vibrator vibrator;
    private AudioManager audioManager;
    private NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notice);
        notice =  findViewById(R.id.switch_notice);
        vibrate = findViewById(R.id.switch_shake);
        sound =  findViewById(R.id.switch_voice);
        findViewById(R.id.iv_setting_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        audioManager= (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),"消息提醒已开启",Toast.LENGTH_SHORT).show();
                } else {
                    notificationManager.cancelAll();
                    Toast.makeText(getApplicationContext(),"消息提醒已关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(getApplicationContext(),"音量已开启",Toast.LENGTH_SHORT).show();


                } else {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Toast.makeText(getApplicationContext(),"静音",Toast.LENGTH_SHORT).show();

                }
            }
        });
        vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vibrator.vibrate(new long[]{1000, 50, 50, 100, 50}, -1);
                    Toast.makeText(NoticeActivity.this, "振动已开启", Toast.LENGTH_SHORT).show();
                } else {
                    vibrator.cancel();
                    Toast.makeText(getApplicationContext(),"振动已关闭",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
