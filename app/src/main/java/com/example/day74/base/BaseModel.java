package com.example.day74.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    public CompositeDisposable compositeDisposable;
    public void addDisposable(Disposable disposable){
        if (compositeDisposable==null)
            compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(disposable);
    }
    public void destroy(){
        if (compositeDisposable!=null&&compositeDisposable.size()>0)
        compositeDisposable.clear();
    }
}
