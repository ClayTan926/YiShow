package cn.tzl.yishow;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.yishow.bean.Comment;

public class NewCommentActivity extends AppCompatActivity {

    @BindView(R.id.iv_comment_back)
    ImageView ivCommentBack;
    @BindView(R.id.add_title)
    EditText addTitle;
    @BindView(R.id.add_content)
    EditText addContent;
    @BindView(R.id.btn_addComment)
    Button btnAddComment;
    private BmobUser bmobUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransparentActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_comment);
        Bmob.initialize(this, "eeeae81bed48e80d6181bdf350980c64");
        bmobUser=BmobUser.getCurrentUser();

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
        Comment comment=new Comment();
        comment.setUsername(bmobUser.getUsername());
        comment.setType("comment");
        comment.setCnun("0");
        comment.setLikenum("0");
        comment.setAvatar("https://note.youdao.com/yws/public/resource/b4b16e1c45c7bd76d48c0cac163b32ac/xmlnote/D207AD89B7E341CFAA893EFF6B9BD261/496");
        comment.setCcontent(addContent.getText().toString().trim());
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Log.e("bmob","添加成功");
                    //Intent intent=new Intent(getApplicationContext(),DisplayFragment.class);
                    setResult(0);
                    finish();
                    //startActivity(intent);
                    Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
                }else{
                    Log.e("bmob","添加失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

    }

    @OnClick({R.id.iv_comment_back, R.id.btn_addComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_comment_back:
                setResult(1);
                finish();
                break;
            case R.id.btn_addComment:
                addNewComment();
                break;
        }
    }
}
