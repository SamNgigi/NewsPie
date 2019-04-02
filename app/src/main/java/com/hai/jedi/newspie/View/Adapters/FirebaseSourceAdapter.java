package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebaseSourceAdapter extends RecyclerView.Adapter<FirebaseSourceAdapter.FBViewHolder> {

    private Context mContext;
    private List<Source> fSources;

    public FirebaseSourceAdapter(Context context, List<Source> fbSources){
        this.mContext = context;
        this.fSources = fbSources;
    }

    public class FBViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.savedSourceChip)
        Chip fbSourceChip;


        public FBViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           /* fbSourceChip.setOnClickListener(this);
            fbSourceChip.setOnCloseIconClickListener(this);*/
        }

        public void bindSource(Source source){
            fbSourceChip.setChipText(source.getSource_name());
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
       View view = LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.firebase_source_item, parent, false);
       return new FBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseSourceAdapter.FBViewHolder holder, int position) {
        holder.bindSource(fSources.get(position));

        /*holder.fbSourceChip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You clicked the close icon", Toast.LENGTH_LONG)
                        .show();
            }
        });*/

        /*holder.fbSourceChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You the chip", Toast.LENGTH_LONG)
                        .show();
            }
        });*/
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return fSources.size();
    }


}
