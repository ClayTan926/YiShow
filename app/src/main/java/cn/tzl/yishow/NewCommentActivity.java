package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCommentActivity extends AppCompatActivity {

    @BindView(R.id.iv_comment_back)
    ImageView ivCommentBack;
    @BindView(R.id.add_title)
    EditText addTitle;
    @BindView(R.id.add_content)
    EditText addContent;
    @BindView(R.id.btn_addComment)
    Button btnAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransparentActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_comment);
        ButterKnife.bind(this);
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
    private void addNewComment(){
        Toast.makeText(this,"发送成功",Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.iv_comment_back, R.id.btn_addComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_comment_back:
                finish();
                break;
            case R.id.btn_addComment:
                addNewComment();
                break;
        }
    }
}
