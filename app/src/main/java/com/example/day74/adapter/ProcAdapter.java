package com.example.day74.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day74.Bean.DatasBean;
import com.example.day74.R;


import java.util.ArrayList;

public class ProcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<DatasBean>list;
    private int VIEW_TYPE_ONE=1;
    private int VIEW_TYPE_TWO=2;
    private OnItemLongClickLister onItemLongClickLister;

    public void setOnItemLongClickLister(OnItemLongClickLister onItemLongClickLister) {
        this.onItemLongClickLister = onItemLongClickLister;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2!=0){
            return VIEW_TYPE_ONE;
        }else {
            return VIEW_TYPE_TWO;
        }
    }

    public ProcAdapter(Context context, ArrayList<DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_ONE){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc1, null);
            return new ViewHolder1(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc, null);
            return new ViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType==VIEW_TYPE_ONE){
            ViewHolder1 holder1= (ViewHolder1) holder;
          holder1.tv_title.setText(list.get(position).getTitle());
        }else {
            ViewHolder2 holder2= (ViewHolder2) holder;
                Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder2.iv_pic);
                holder2.tv_title.setText(list.get(position).getTitle());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickLister.onItemLongClick(position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder1 extends RecyclerView.ViewHolder {
       TextView tv_title;
        public ViewHolder1(View inflate) {
            super(inflate);
            tv_title=inflate.findViewById(R.id.tv_title);
        }
    }


    private class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView iv_pic;
        TextView tv_title;
        public ViewHolder2(View inflate) {
            super(inflate);
            iv_pic=inflate.findViewById(R.id.iv_pic);
            tv_title=inflate.findViewById(R.id.tv_title);
        }
    }
    public interface OnItemLongClickLister{
        void onItemLongClick(int position);
    }
}
