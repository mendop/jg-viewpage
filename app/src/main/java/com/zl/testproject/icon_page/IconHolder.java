package com.zl.testproject.icon_page;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zl.testproject.R;


public class IconHolder extends RecyclerView.ViewHolder {
    public ImageView iv;
    public TextView title;

    public IconHolder(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.iv);
        title = itemView.findViewById(R.id.title);
    }
}
