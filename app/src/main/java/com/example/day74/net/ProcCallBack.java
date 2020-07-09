package com.example.day74.net;

public interface ProcCallBack<T> {
    void onSucess(T t);
    void onFain(String str);

}
