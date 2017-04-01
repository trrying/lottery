package com.owm.lottery.model.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owm.lottery.model.event.IEvent;

import io.reactivex.subjects.PublishSubject;

/**
 * App holder
 * Created by ouweiming on 2017/4/1.
 */

public class AppHolder {

    private static PublishSubject<IEvent> sEventBus;
    private static final Object sRxLock = new Object();

    private static Gson gson;
    private static final Object sGsonLock = new Object();

    public static PublishSubject<IEvent> getEventBus() {
        if (sEventBus == null) {
            synchronized (sRxLock) {
                if (sEventBus == null) {
                    sEventBus = PublishSubject.create();
                }
            }
        }
        return sEventBus;
    }

    public static Gson getGsonExpose() {
        if (gson == null) {
            synchronized (sGsonLock) {
                if (gson == null) {
                    gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                }
            }
        }
        return gson;
    }

}
