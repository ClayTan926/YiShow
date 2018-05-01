package cn.tzl.yishow.bean;

import android.provider.ContactsContract;

import cn.bmob.v3.BmobObject;

/**
 * Created by Tanzl on 2018/5/1.
 * Class Comment:
 */

public class Info extends BmobObject {
    private String title;
    private String content;
    private String time;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
