package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        @BindView(R.id.headlineDescription)
        TextView description;

        @BindView(R.id.publishedAt)
        TextView publishedAt;

        @BindView(R.id.toArticle)
        TextView toFullArticle;








        public HeadlineViewHolder(View viewItem){
            super(viewItem);
            ButterKnife.bind(this, viewItem);

        }

        public void bindHeadline(Headline headline){
            Picasso.get().load(headline.getImg_url()).into(headlineImage);
            title.setText(headline.getTitle());
            description.setText(headline.getDescription());
            publishedAt.setText(headline.getPublished_at());
            toFullArticle.setOnClickListener(this);
        }


        @Override
        public void onClick(View view){
            //Getting the layout position of what we have just clicked
            int itemPosition = getLayoutPosition();
            // Grabbing the article url of the article we have just clicked on.
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mHeadlines.get(itemPosition).getArticle_url()));
            // Starting the activity.
            view.getContext().startActivity(webIntent);

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
