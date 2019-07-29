package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hai.jedi.newspie.Models.Headline;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.View.Activities.ArticleActivity;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.BindView;

public class HeadlineListAdapter
        extends RecyclerView.Adapter<HeadlineListAdapter.HeadlineViewHolder> {

    private static final String TAG = HeadlineListAdapter.class.getSimpleName().toUpperCase();

    private Context mContext;
    private List<Headline> mHeadlines;
    private SharedViewModel sharedViewModel;


    public HeadlineListAdapter(Context context, List<Headline> headlines){
        this.mContext = context;
        this.mHeadlines = headlines;

        /* *
         * Initializing the sharedViewModel. We use this to pass headline data from adapter to
         * ArticleFragment.
         * */
        sharedViewModel = ViewModelProviders.of((FragmentActivity) mContext)
                .get(SharedViewModel.class);
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

        @BindView(R.id.sourceName)
        TextView sourceName;








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
            sourceName.setText(headline.getSource().getSource_name());
        }


        @Override
        public void onClick(View view){
            //Getting the layout position of what we have just clicked
            int itemPosition = getLayoutPosition();
            String article_url = mHeadlines.get(itemPosition).getArticle_url();
            Headline article = mHeadlines.get(itemPosition);
            sharedViewModel.setSelected_headline(article);
            CustomTabsIntent.Builder customTabBuilder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = customTabBuilder.build();
            customTabsIntent.launchUrl(view.getContext(), Uri.parse(article_url));
            /*Intent intent = new Intent(view.getContext(), ArticleActivity.class);
            Headline article = mHeadlines.get(itemPosition);
            intent.putExtra("article", Parcels.wrap(article));
            view.getContext().startActivity(intent);*/

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
