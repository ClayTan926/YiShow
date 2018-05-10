package cn.tzl.yishow.bean;

import java.util.List;

/**
 * Created by Netted on 2018/5/8.
 */

public class BodyParameter {
    private String request_id;
    private String result;
    private String image_id;
    private List humanbodies;
    private Integer time_used;
    private String error_message;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public List getHumanbodies() {
        return humanbodies;
    }

    public void setHumanbodies(List humanbodies) {
        this.humanbodies = humanbodies;
    }

    public Integer getTime_used() {
        return time_used;
    }

    public void setTime_used(Integer time_used) {
        this.time_used = time_used;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
