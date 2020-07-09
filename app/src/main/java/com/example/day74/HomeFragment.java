package com.example.day74;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day74.Bean.DatasBean;
import com.example.day74.Bean.ProcBean;
import com.example.day74.adapter.ProcAdapter;
import com.example.day74.base.BaseApp;
import com.example.day74.base.BaseFragment;
import com.example.day74.db.DatasBeanDao;
import com.example.day74.presenter.ProcPresenter;
import com.example.day74.view.ProcView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<ProcPresenter> implements ProcView, ProcAdapter.OnItemLongClickLister {


    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    private ArrayList<DatasBean> list;
    private ProcAdapter adapter;


    @Override
    protected void initPresenter() {
        mPresnter=new ProcPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initLister() {

    }

    @Override
    protected void initView() {
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
       rvHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new ProcAdapter(getActivity(), list);
        rvHome.setAdapter(adapter);
        adapter.setOnItemLongClickLister(this);
    }

    @Override
    protected void initData() {
        mPresnter.getData();
    }

    @Override
    public void setData(ProcBean procBean) {
        list.addAll(procBean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {

    }

    @Override
    public void onItemLongClick(int position) {
        DatasBeanDao beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        beanDao.insertOrReplace(list.get(position));
        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
    }
}
