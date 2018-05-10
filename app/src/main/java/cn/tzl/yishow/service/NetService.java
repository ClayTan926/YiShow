package cn.tzl.yishow.service;

import java.io.File;

import cn.tzl.yishow.bean.BodyParameter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Netted on 2018/5/8.
 */

public interface NetService {
    @FormUrlEncoded
    @POST("humanbodypp/v1/detect")
    Call<BodyParameter> bodyDetect(@Field("api_key") String  apiKey,
                                @Field("api_secret") String apiSecret,
                                @Part("image_file") File image);

}

