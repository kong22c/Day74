package com.example.day74.presenter;

import com.example.day74.Bean.ProcBean;
import com.example.day74.base.BasePreseneter;
import com.example.day74.model.ProcModel;
import com.example.day74.net.ProcCallBack;
import com.example.day74.view.ProcView;

public class ProcPresenter extends BasePreseneter<ProcView> {

    private ProcModel procModel;

    @Override
    protected void initModel() {
        procModel = new ProcModel();
        addModel(procModel);
    }
    public void getData(){
        procModel.getData(new ProcCallBack<ProcBean>() {
            @Override
            public void onSucess(ProcBean procBean) {
                mView.setData(procBean);
            }

            @Override
            public void onFain(String str) {
                mView.showToast(str);
            }
        });
    }
}
