package com.demo.textsizechange;

import android.app.Application;

/**
 * Created by zsj on 2018/6/27.
 */

public class MyApplication extends Application{
    private PreferencesHelper ph;
    private static MyApplication instance;

    // 单例模式获取唯一的Application实例
    public static Application getInstance() {
        return instance.getApplication();
    }
    public static MyApplication getMyInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;//初始化
        ph = new PreferencesHelper(getApplication(), "test");
    }

    public PreferencesHelper getPreferencesHelper() {
        return ph;
    }

    /**
     *
     * @return 获取字体缩放比例
     */
    public float getFontScale(){
        int currentIndex= ph.getValueInt("currentIndex",1);
        return 1+currentIndex*0.1f;
    }


    private Application getApplication(){
        return  this;
    }


}
