package com.owm.lottery.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.xutils.x;

/**
 * 偏好参数 工具类
 * Created by ouweiming on 2017/4/5.
 */

public class SharedPreferencesUtil {
    /**  默认偏好参数文件名  */
    private static final String SP_NAME_DEFAULT = "com.owm.lottery_default";

    private static final String SP_KEY_COMPUTE = "compute_result";

    public static SharedPreferences getSP() {
        return x.app().getSharedPreferences(SP_NAME_DEFAULT, Context.MODE_PRIVATE);
    }

    /**
     * 保存计算进度
     * @param compute 计算进度  2,2,2,2
     */
    public static void putCompute(String compute){
        SharedPreferences.Editor editor = getSP().edit();
        editor.putString(SP_KEY_COMPUTE, compute);
        editor.commit();
    }

    public static String getCompute(){
        String result = "";
        getSP().getString(SP_KEY_COMPUTE, "2,2,1,0");
        return result;
    }
}
