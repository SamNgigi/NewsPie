package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.graphics.PorterDuff;
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
    public SourceListAdapter(Context context, List<Source> sources,
                             List<String> saved_sources, List<Source> fbSources) {
        this.mContext = context;
        this.mSources = sources;
        this.fbSourceList = saved_sources;
        this.fSources = fbSources;

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
               /*Toast Edit manenos*/
               Toast toast_message = Toast.makeText(view.getContext(),
                       String.format("Loading %s headlines!",
                               mSources.get(itemPosition).getSource_name()),
                       Toast.LENGTH_LONG);
               View toast_view =  toast_message.getView();
               toast_view.getBackground()
                       .setColorFilter(ContextCompat.getColor(toast_view.getContext(),
                               R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
               toast_message.show();
               // Passing the source_id data to sharedViewModel based on source clicked
               sharedViewModel.setSelected_sourceId(mSources.get(itemPosition).getSource_id());
           }
           if(view == sourceBookmark){
               mSource = mSources.get(itemPosition);
               sharedViewModel.setSelected_source(mSource);

               /* *
               *  Todo - May have to migrate to fire-store
               * */

               bookMarkedSources = FirebaseDatabase.getInstance()
                       .getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
               fbService = new FirebaseService(bookMarkedSources);
              /* Source fSource = fbService.retrieveSource(mSource);*/
               if(!mSource.getSaved_status()){
                   fbService.addSource(mSource);
                   sourceBookmark.setColorFilter(
                           ContextCompat.getColor(view.getContext(), R.color.colorPrimary));

                   Toast toast_message = Toast.makeText(view.getContext(),
                           String.format("%s bookmarked!",
                                   mSource.getSource_name()),
                           Toast.LENGTH_LONG);
                   View toast_view =  toast_message.getView();
                   toast_view.getBackground()
                           .setColorFilter(ContextCompat.getColor(toast_view.getContext(),
                                   R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
                   toast_message.show();

               } else {
                   Log.d(TAG, mSource.getSource_Uid());
                   fbService.removeSource(mSource);
                   sourceBookmark.setColorFilter(
                           ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                   Toast toast_message = Toast.makeText(view.getContext(),
                           String.format("%s Bookmarked Removed!",
                                   mSource.getSource_name()),
                           Toast.LENGTH_LONG);
                   View toast_view =  toast_message.getView();
                   toast_view.getBackground()
                           .setColorFilter(ContextCompat.getColor(toast_view.getContext(),
                                   R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
                   toast_message.show();
               }


           }

        }
    }

    // Required RecyclerView override methods
    @NonNull
    @Override
    public SourceListAdapter.SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
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
            int fSourceIndex = fbSourceList.indexOf(mSources.get(position).getSource_id());
            mSources.set(position, fSources.get(fSourceIndex));
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
