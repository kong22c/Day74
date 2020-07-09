package com.example.day74.net;

import com.example.day74.Bean.ProcBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String BASS="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Flowable<ProcBean>getproc();
}
