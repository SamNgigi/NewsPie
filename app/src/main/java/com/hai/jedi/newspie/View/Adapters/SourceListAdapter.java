package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceListAdapter
        extends RecyclerView.Adapter<SourceListAdapter.SourceViewHolder>{

    private Context mContext;
    private List<Source> mSources;

    // Our Adapter constructor.
    public SourceListAdapter(List<Source> sources){
        this.mSources = sources;
    }

    // Our ViewHolder class
    public class SourceViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sourceImageView)
        ImageView mSourceImageView;
        @BindView(R.id.sourceName)
        TextView mSourceName;
        @BindView(R.id.sourceCategory)
        TextView mSourceCategory;
        @BindView(R.id.sourceUrl)
        TextView mSourceUrl;



        private Context mContext;

        // Our ViewHolder constructor
        public SourceViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindSource(Source source){
           mSourceName.setText(source.getSource_name());
           mSourceCategory.setText(source.getSource_category());
           mSourceUrl.setText(Html.fromHtml(itemView.getContext().getString(R.string.lorem)));
           mSourceUrl.setText(source.getSource_url());

        }
    }

    // Required RecyclerView override methods
    @NonNull
    @Override
    public SourceListAdapter.SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.source_list_item, parent, false);
        return new SourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceListAdapter.SourceViewHolder viewHolder,
                                int position){
        viewHolder.bindSource(mSources.get(position));
    }

    @Override
    public int getItemCount(){
        return mSources.size();
    }

}
