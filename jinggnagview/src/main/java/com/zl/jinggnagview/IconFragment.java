package com.zl.jinggnagview;

import static com.zl.jinggnagview.JingGangView.ITEM_COUNT;
import static com.zl.jinggnagview.JingGangView.ROW_HEIGTH;
import static com.zl.jinggnagview.JingGangView.SPAN_COUNT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IconFragment<B, H extends RecyclerView.ViewHolder> extends Fragment {
    private IIconAdapterListener<B, H> iconAdapterListener;
    private List<B> list = new ArrayList<>();

    public IconFragment() {
        super();
    }


    public IconFragment(IIconAdapterListener<B, H> iconAdapterListener, List<B> list) {
        super();
        this.iconAdapterListener = iconAdapterListener;
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) list);
        setArguments(bundle);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout frameLayout = new FrameLayout(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(lp);
        return frameLayout;
//        return inflater.inflate(R.layout.fg_icon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            //noinspection unchecked
            list = (List<B>) getArguments().get("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() > 0) {
//            RecyclerView recyclerView = view.findViewById(R.id.recycler);
            RecyclerView recyclerView = new RecyclerView(requireActivity());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            recyclerView.setLayoutParams(lp);
            ((ViewGroup) view).addView(recyclerView);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    list.size() > SPAN_COUNT ? ROW_HEIGTH * (int) Math.ceil((float) ITEM_COUNT / (float) SPAN_COUNT) : ROW_HEIGTH);
            recyclerView.setLayoutParams(layoutParams);
            IconAdapter<B, H> iconAdapter = new IconAdapter<>(getActivity(), list);
            iconAdapter.setIconAdapterListener(iconAdapterListener);
            recyclerView.setAdapter(iconAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
