package com.zl.testproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zl.jinggnagview.IIconAdapterListener;
import com.zl.jinggnagview.IconFragment;
import com.zl.jinggnagview.JingGangView;
import com.zl.testproject.R;
import com.zl.testproject.icon_page.IconBean;
import com.zl.testproject.icon_page.IconHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private JingGangView<IconBean, IconHolder> jgView;
    private List<IconFragment> list = new ArrayList<>();
    private List<IconBean> data = new ArrayList<>();
    private List<Integer> heights = new ArrayList<>();
    private final static String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test);
        jgView = findViewById(R.id.jgView);

        data.clear();
        for (int i = 0; i < 38; i++) {
            data.add(new IconBean("类别" + i, R.drawable.icon));
        }

        jgView.setActivity(this)
                .setBackPadding(0, 10, 0, 10)
                .setItemCount(9)
                .setSpanCount(3)
                .setData(data, new IIconAdapterListener<IconBean, IconHolder>() {
                    @Override
                    public IconHolder getHoler(ViewGroup parent) {
                        return new IconHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon, parent, false));
                    }

                    @Override
                    public void onBindViewHolder(@NonNull IconHolder holder, int position, List<IconBean> datas) {
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(TestActivity.this, datas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        holder.iv.setImageResource(datas.get(position).getRes());
                        holder.title.setText(datas.get(position).getTitle());

                    }
                });
    }
}
