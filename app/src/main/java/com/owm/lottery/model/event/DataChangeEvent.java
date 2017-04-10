package com.owm.lottery.model.event;

import com.owm.lottery.model.event.common.IEvent;

/**
 * 划图操作
 * Created by ouweiming on 2017/4/5.
 */

public class DataChangeEvent<T> implements IEvent {

    private String mSourceType;
    /**  操作类型  */
    private int mOperationType;
    /**  划图数据  */
    private T data;

    public DataChangeEvent(String sourceType, int operationType) {
        mSourceType = sourceType;
        mOperationType = operationType;
    }

    private DataChangeEvent(int operationType, T data) {
        mOperationType = operationType;
        this.data = data;
    }

    public static <T>DataChangeEvent create(int operationType, T data) {
        return new DataChangeEvent<T>(operationType, data);
    }

    public static DataChangeEvent create(String sourceType, int operationType) {
        return new DataChangeEvent(sourceType, operationType);
    }

    public int getOperationType() {
        return mOperationType;
    }

    public void setOperationType(int operationType) {
        this.mOperationType = operationType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSourceType() {
        return mSourceType;
    }

    public void setSourceType(String sourceType) {
        mSourceType = sourceType;
    }
}
