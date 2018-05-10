package cn.tzl.yishow.bean;

/**
 * Created by Netted on 2018/5/8.
 */

public class HumanBody {
    private Float confidence;
    private Object humanbody_rectangle;
    private Object attributes;

    public Float getConfidence() {
        return confidence;
    }

    public void setConfidence(Float confidence) {
        this.confidence = confidence;
    }

    public Object getHumanbody_rectangle() {
        return humanbody_rectangle;
    }

    public void setHumanbody_rectangle(Object humanbody_rectangle) {
        this.humanbody_rectangle = humanbody_rectangle;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
}
