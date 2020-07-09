package com.example.day74.model;

import com.example.day74.Bean.ProcBean;
import com.example.day74.base.BaseModel;
import com.example.day74.net.ApiService;
import com.example.day74.net.ProcCallBack;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProcModel extends BaseModel {
    public void getData(ProcCallBack<ProcBean>callBack){
        ResourceSubscriber<ProcBean> resourceSubscriber = new Retrofit.Builder()
                .baseUrl(ApiService.BASS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getproc()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<ProcBean>() {
                    @Override
                    public void onNext(ProcBean procBean) {
                        callBack.onSucess(procBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFain(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addDisposable(resourceSubscriber);

    }
}
