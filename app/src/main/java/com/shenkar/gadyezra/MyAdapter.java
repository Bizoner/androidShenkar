package com.shenkar.gadyezra;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * demonstrating
 * - recycler view (https://developer.android.com/guide/topics/ui/layout/recyclerview)
 * - live data (https://developer.android.com/topic/libraries/architecture/livedata)
 *
 * -au
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> data) {
        mDataset = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitle.setText(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTitle;
        private BirthNameDate currentItem;

        public ViewHolder(ViewGroup v) {
            super(v);
            mTitle = v.findViewById(R.id.title);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sLiveCurrentItem.postValue(currentItem);
        }


        public void setCurrentItem(BirthNameDate currentItem) {
            this.currentItem = currentItem;
        }
    }

    /**
     * LiveData is allowed to be declared static without danger of memory leak
     * It actually holds a static reference to the activity inside
     * But it's lifecycle aware, so it does all the work for us (keeping a weak reference,
     * replacing it when a new activity is created), so the new activity gets the most current data.
     *
     * -au
     */
    private static MutableLiveData<BirthNameDate> sLiveCurrentItem;

    public LiveData<BirthNameDate> getCurrentItemLive() {
        if (sLiveCurrentItem == null) {
            sLiveCurrentItem = new MutableLiveData<>();
        }
        return sLiveCurrentItem;
    }

}
