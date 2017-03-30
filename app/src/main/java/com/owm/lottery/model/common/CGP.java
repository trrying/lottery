package com.owm.lottery.model.common;

import android.content.Context;
import android.graphics.Typeface;

/**
 * CommonGlobalProperty 全局字段
 * Created by ouweiming on 2017/3/30.
 */

public class CGP {


    private static Typeface mCharType;   // 中文字体
    private static Typeface mNumberType;    // 英文数字字体

    public static Typeface getCharTypeFace(Context context){
        if(mCharType == null){
            mCharType = Typeface.createFromAsset(context.getAssets(),"fonts/fzzqhjt.ttf");
        }

        return mCharType;
    }

    public static Typeface getNumberTypeFace(Context context){
        if(mNumberType == null){
            mNumberType = Typeface.createFromAsset(context.getAssets(),"fonts/arialbd.ttf");
        }

        return mNumberType;
    }


}
