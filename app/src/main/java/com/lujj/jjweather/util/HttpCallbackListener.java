package com.lujj.jjweather.util;

/**
 * Created by lujj on 16/6/30.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
