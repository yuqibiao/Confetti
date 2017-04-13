package com.yyyu.confetti.utils;

import android.support.annotation.NonNull;

import java.util.Vector;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class RxBus {
    private static RxBus instance;
    private Vector<Subject> subjectList = new Vector<>();

    private RxBus() {
    }

    public static synchronized RxBus getInstance() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    public synchronized <T> Observable<T> register(@NonNull Object object) {
        Subject<T, T> subject = PublishSubject.create();
        subjectList.add(subject);
        return subject;
    }

    public synchronized void unregister(Object object) {
        subjectList.remove(object);
    }

    public void post(@NonNull Object content) {
        synchronized (this) {
            for (Subject subject : subjectList) {
                if (subject != null) {
                    subject.onNext(content);
                }
            }
        }
    }

}