package com.zl.jinggnagview;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PageAdapter<B,H extends RecyclerView.ViewHolder> extends FragmentStateAdapter {

    private final Context context;
    private final List<IconFragment<B,H>> list;

    public PageAdapter(Context context, List<IconFragment<B,H>> list, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    private void changeFragmentHeight(Fragment fragment, int heightInDp) {
        View rootView = fragment.getView();
        if (rootView != null) {
            int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp, context.getResources().getDisplayMetrics());
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightInPx));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
