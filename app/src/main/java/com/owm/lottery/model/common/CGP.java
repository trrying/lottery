package com.owm.lottery.model.common;

import android.content.Context;
import android.graphics.Typeface;

/**
 * CommonGlobalProperty 全局字段
 * Created by ouweiming on 2017/3/30.
 */

public class CGP {

    public static class HandlerEvent{
        /**  添加 操作  */
        public static final int TYPE_ADD = 0x1;
        /**  添加完成 操作  */
        public static final int TYPE_ADDED = 0x2;
        /**  更新操作  */
        public static final int TYPE_UPDATE = 0x3;
        /**  删除操作  */
        public static final int TYPE_DELETE = 0x4;
        /**  有改变操作  */
        public static final int TYPE_CHANGE = 0x5;
    }


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
