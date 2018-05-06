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

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.yishow.bean.Comment;
import cn.tzl.yishow.bean.MyUser;

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
    private MyUser user;
    private String url;

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
        comment.setAvatar(getAvatar());
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

    private String getAvatar() {
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("username", bmobUser.getUsername());
        query.setLimit(1);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (list != null) {
                    Log.e("person", "done: " + list.size());
                    user = list.get(0);
                    if (user != null) {
                        if (user.getAvatar() != null || !user.getAvatar().equals("")) {
                            url = user.getAvatar();

                        }
                    }

                } else {
                    Log.e("person", "done: " + e);
                }
            }
        });
        return url;

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
