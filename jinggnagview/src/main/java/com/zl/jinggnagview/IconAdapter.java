package com.zl.jinggnagview;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IconAdapter<B, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    private Context context;
    private List<B> list;
    private IIconAdapterListener<B,H> iconAdapterListener;


    public IconAdapter(Context context, List<B> list) {
        this.context = context;
        this.list = list;
    }

    public void setIconAdapterListener(IIconAdapterListener<B,H> iconAdapterListener) {
        this.iconAdapterListener = iconAdapterListener;
    }

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return iconAdapterListener.getHoler(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        iconAdapterListener.onBindViewHolder(holder, position,list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
