package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.Services.FirebaseService;
import com.hai.jedi.newspie.ViewModel.FirebaseViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceListAdapter
        extends RecyclerView.Adapter<SourceListAdapter.SourceViewHolder>{

    private  final String TAG = SourceListAdapter.class.getSimpleName().toUpperCase();

    private Context mContext;
    private List<Source> mSources;
    private List<Source> fSources;
    private List<String> fbSourceList;
    private Source mSource;
    private SharedViewModel sharedViewModel;
    private FirebaseViewModel firebaseViewModel;

    private DatabaseReference bookMarkedSources;
    private FirebaseService fbService;

    // Our Adapter constructor.
    public SourceListAdapter(Context context, List<Source> sources, List<String> saved_sources) {
        this.mContext = context;
        this.mSources = sources;
        this.fbSourceList = saved_sources;

        /* *
        * Initializing the sharedViewModel. We use this to pass source_id data from adapter to
        * Headline fragment.
        * */
        sharedViewModel = ViewModelProviders.of((FragmentActivity) mContext)
                        .get(SharedViewModel.class);

        firebaseViewModel = ViewModelProviders.of((FragmentActivity) mContext)
                        .get(FirebaseViewModel.class);


    }

    // Our ViewHolder class
    public class SourceViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.sourceName)
        TextView mSourceName;
        @BindView(R.id.sourceCategory)
        TextView mSourceCategory;
        @BindView(R.id.bookMark)
        ImageView sourceBookmark;



        private Context mContext;
        private boolean bookmarked = false;

        // Our ViewHolder constructor
        public SourceViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            // Listening for a click on our recycler view
            mSourceName.setOnClickListener(this);
            sourceBookmark.setOnClickListener(this);
        }

        public void bindSource(Source source){
           mSourceName.setText(source.getSource_name());
           mSourceCategory.setText(source.getSource_category());
        }

        // Handling click event on a source item
        @Override
        public void onClick(View view){

            int itemPosition = getLayoutPosition();
           if(view == mSourceName) {
               // Passing the source_id data to sharedViewModel based on source clicked
               sharedViewModel.setSelected_sourceId(mSources.get(itemPosition).getSource_id());
           }
           if(view == sourceBookmark){
               mSource = mSources.get(itemPosition);
               sharedViewModel.setSelected_source(mSource);



               /*for(Source s: fSources){
                   fbSourceList.add(s.getSource_id());
               }


               Log.d(TAG, fbSourceList.toString());*/


               /* *
               *  Todo - persist the ui state, using onSavedState
               *  Todo - refresh ui state by syncing api call list with firebase
               *  Todo - May have to migrate to firestore
               *
               * */

               bookMarkedSources = FirebaseDatabase.getInstance()
                       .getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
               fbService = new FirebaseService(bookMarkedSources);
               Source fSource = fbService.retrieveSource(mSource);
               if(!fSource.getSaved_status()){
                   sourceBookmark.setColorFilter(
                           ContextCompat.getColor(view.getContext(), R.color.colorPrimary));
                   Toast.makeText(view.getContext(),
                           String.format("%s Bookmarked!", mSource.getSource_name()),
                           Toast.LENGTH_LONG).show();

               } else {
                   sourceBookmark.setColorFilter(
                           ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                   Toast.makeText(view.getContext(),
                           String.format("%s Bookmarked Removed!", mSource.getSource_name()),
                           Toast.LENGTH_LONG).show();
                   /*mSources.get(position).setSaved_status(true);*/
               }


           }

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
        if(!fbSourceList.contains(mSources.get(position).getSource_id())){
            viewHolder.bindSource(mSources.get(position));
            viewHolder.sourceBookmark.setColorFilter(
                    ContextCompat.getColor(viewHolder.sourceBookmark.getContext(),
                                            R.color.colorPrimaryDark));
        } else {
            viewHolder.bindSource(mSources.get(position));
            viewHolder.sourceBookmark.setColorFilter(
                    ContextCompat.getColor(viewHolder.sourceBookmark.getContext(),
                            R.color.colorPrimary));
        }

    }

    @Override
    public int getItemCount(){
        return mSources.size();
    }


}
