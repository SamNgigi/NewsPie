package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hai.jedi.newspie.Models.Headline;
import com.hai.jedi.newspie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.BindView;

public class HeadlineListAdapter
        extends RecyclerView.Adapter<HeadlineListAdapter.HeadlineViewHolder> {

    private Context mContext;
    private List<Headline> mHeadlines;


    public HeadlineListAdapter(Context context, List<Headline> headlines){
        this.mContext = context;
        this.mHeadlines = headlines;
    }

    public class HeadlineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Context mContext;

        @BindView(R.id.headlineImg)
        ImageView headlineImage;

        @BindView(R.id.headlineTitle)
        TextView title;



        public HeadlineViewHolder(View viewItem){
            super(viewItem);
            ButterKnife.bind(this, viewItem);

        }

        public void bindHeadline(Headline headline){
            Picasso.get().load(headline.getImg_url()).into(headlineImage);
            title.setText(headline.getTitle());
        }


        @Override
        public void onClick(View view){

        }

    }

    @NonNull
    @Override
    public HeadlineListAdapter.HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                     int ViewType){
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.headline_list_item, parent, false);
        return new HeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineListAdapter.HeadlineViewHolder headlineViewHolder,
                                 int position){
        headlineViewHolder.bindHeadline(mHeadlines.get(position));

    }

    @Override
    public int getItemCount(){return mHeadlines.size();}

}