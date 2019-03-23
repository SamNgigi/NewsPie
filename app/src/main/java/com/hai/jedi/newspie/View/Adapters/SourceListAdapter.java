package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.Services.FirebaseHelper;
import com.hai.jedi.newspie.Services.FirebaseService;
import com.hai.jedi.newspie.View.Fragments.SourceListFragment;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;
import com.hai.jedi.newspie.ViewModel.SourceViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Source mSource;
    private SharedViewModel sharedViewModel;

    private DatabaseReference bookMarkedSources;
    private FirebaseService fbService;
    private FirebaseHelper firebaseHelper;

    // Our Adapter constructor.
    public SourceListAdapter(Context context, List<Source> sources) {
        this.mContext = context;
        this.mSources = sources;

        /* *
        * Initializing the sharedViewModel. We use this to pass source_id data from adapter to
        * Headline fragment.
        * */
        sharedViewModel = ViewModelProviders.of((FragmentActivity) mContext)
                        .get(SharedViewModel.class);
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

               /*
               * TODO - Remove this once here. Compare firebase and News Api
               * TODO - In the adapter. Then just check for boolean field in Source object
               * */

               bookMarkedSources = FirebaseDatabase.getInstance()
                       .getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
               fbService = new FirebaseService(bookMarkedSources);

               ArrayList<String> savedSourceIds = fbService.retrieveSources(mSource).source_ids;

               if(!savedSourceIds.contains(mSource.getSource_id())){
                   Toast.makeText(view.getContext(),
                           String.format("%s Bookmarked!", mSource.getSource_name()),
                           Toast.LENGTH_LONG).show();
               } else {
                   Toast.makeText(view.getContext(),
                           String.format("%s Bookmarked Removed!", mSource.getSource_name()),
                           Toast.LENGTH_LONG).show();
               }


               // DB manenos
               // Listening for changes to persist.
               /*bookMarkedSources.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       final ArrayList<String> favSources = new ArrayList<>();
                        // Loop to append snapshots to our source array.
                       for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                           // Adding a source id to a list
                           favSources.add(Objects.requireNonNull(snapshot.getValue(Source.class))
                                    .getSource_id());

                       }
                        // Checking if we had persisted the source
                       if(!favSources.contains(mSource.getSource_id())){
                           // Setting our source Uid to the firebase push id.
                           mSource.setSource_Uid(bookMarkedSources.push().getKey());
                           // Adding our source object as a child of that reference
                           bookMarkedSources.child(mSource.getSource_Uid()).setValue(mSource);
                           //Log.d(TAG, pushId);
                           sourceBookmark.setColorFilter(
                                   ContextCompat.getColor(view.getContext(), R.color.colorPrimary));
                           Toast.makeText(view.getContext(),
                                   "Source is saved",
                                   Toast.LENGTH_LONG).show();

                       } else {
                           // Removing the reference
                           bookMarkedSources.child(mSource.getSource_Uid()).setValue(null);
                           // bookMarkedSources.getRef().removeValue();
                           Toast.makeText(view.getContext(),
                                   "Source bookmark has been removed",
                                   Toast.LENGTH_LONG).show();
                           sourceBookmark.setColorFilter(
                                   ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Todo - Something.
                   }
               });*/
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
        viewHolder.bindSource(mSources.get(position));
    }

    @Override
    public int getItemCount(){
        return mSources.size();
    }


}
