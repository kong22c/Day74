package com.example.day74;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day74.Bean.DatasBean;
import com.example.day74.adapter.ProcAdapter;
import com.example.day74.base.BaseApp;
import com.example.day74.db.DatasBeanDao;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollFragment extends Fragment implements ProcAdapter.OnItemLongClickLister {


    private RecyclerView mRvColl;
    private ArrayList<DatasBean> list;
    private ProcAdapter adapter;
    private boolean isView=false;
    private int posi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_coll, container, false);
        initViews(inflate);
        isView=true;
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isView){
            initData();
        }
    }

    private void initData() {
        DatasBeanDao beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        List<DatasBean> datasBeans = beanDao.loadAll();
        list.clear();
        list.addAll(datasBeans);
        adapter.notifyDataSetChanged();
    }

    private void initViews(View view) {
        mRvColl = view.findViewById(R.id.rv_coll);
        mRvColl.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvColl.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new ProcAdapter(getActivity(), list);
        mRvColl.setAdapter(adapter);
        adapter.setOnItemLongClickLister(this);
        registerForContextMenu(mRvColl);
    }

    @Override
    public void onItemLongClick(int position) {
posi=position;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"删除");
        menu.add(0,1,0,"修改");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                DatasBeanDao beanDao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
                beanDao.delete(list.get(posi));
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                initData();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
