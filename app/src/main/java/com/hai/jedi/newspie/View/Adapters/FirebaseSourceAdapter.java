package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hai.jedi.newspie.Models.Source;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseSourceAdapter extends RecyclerView.Adapter<FirebaseSourceAdapter.FBViewHolder> {

    private Context mContext;
    private List<Source> fSources;

    public FirebaseSourceAdapter(Context context, List<Source> fbSources){
        this.mContext = context;
        this.fSources = fbSources;
    }

    public class FBViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public FBViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }


    @NonNull
    @Override
    public FirebaseSourceAdapter.FBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseSourceAdapter.FBViewHolder holder, int position) {

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 0;
    }


}
