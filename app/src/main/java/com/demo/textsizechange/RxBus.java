package com.demo.textsizechange;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by RJS on 2016/7/18.
 */
public class RxBus {
    private ConcurrentHashMap<Object, List<Subject>> maps = new ConcurrentHashMap<>();
    private static RxBus instance;

    private RxBus() {
    }
    private static class RxBusHolder{
        private static RxBus instance = new RxBus();
    }
    public static   RxBus getInstance() {
        return RxBusHolder.instance;
    }

    @SuppressWarnings("unchecked")
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjects = maps.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            maps.put(tag, subjects);
        }
        Subject<T> subject = PublishSubject.<T>create();
        subjects.add(subject);
        return subject;
    }

    @SuppressWarnings("unchecked")
    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = maps.get(tag);
        if (subjects != null) {
            subjects.remove((Subject) observable);
            if (subjects.isEmpty()) {
                maps.remove(tag);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void post(@NonNull Object o) {
        post(o.getClass().getSimpleName(), o);
    }

    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object o) {
        List<Subject> subjects = maps.get(tag);
        if (subjects != null && !subjects.isEmpty()) {
            for (Subject s : subjects) {
                s.onNext(o);
            }
        }
    }

    /**

     private Observable<String> observable;
     observable = RxBus.getInstance().register(getClassName(), String.class);

     observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
    @Override
    public void call(String s) {
    Log.e("zhang", "receiver "+s);
    }
    });

     RxBus.getInstance().unregister("zhang", zhang);

     RxBus.getInstance().post("zhang", "传递数据++++++");


     */
}