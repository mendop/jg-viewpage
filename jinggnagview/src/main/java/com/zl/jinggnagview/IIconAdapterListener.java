package com.zl.jinggnagview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public interface IIconAdapterListener<B, H> {

    H getHoler(ViewGroup parent);

    void onBindViewHolder(@NonNull H holder, int position, List<B> datas);
}
