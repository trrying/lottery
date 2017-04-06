package com.owm.lottery.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 偏好参数 工具类
 * Created by ouweiming on 2017/4/5.
 */

public class SharedPreferencesUtil {
    /**  默认偏好参数文件名  */
    private static final String SP_NAME_DEFAULT = "com.owm.lottery_default";

    private static final String SP_KEY_COMPUTE = "compute_result";

    public static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(SP_NAME_DEFAULT, Context.MODE_PRIVATE);
    }

    /**
     * 保存计算进度
     * @param context 上下文
     * @param compute 计算进度  2,2,2,2
     */
    public static void putCompute(Context context, String compute){
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString(SP_KEY_COMPUTE, compute);
        editor.commit();
    }

    public static String getCompute(Context context){
        String result = "";
        getSP(context).getString(SP_KEY_COMPUTE, "2,2,1,0");
        return result;
    }
}
