package com.hai.jedi.newspie.Services;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hai.jedi.newspie.Models.Source;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FirebaseService {

    public static final String TAG = FirebaseService.class.getSimpleName().toUpperCase();

    DatabaseReference firebaseDB;
    Boolean saved = null;
    FirebaseHelper firebaseHelper;
    ArrayList <Source> source_objects = new ArrayList<>();
    ArrayList<String> saved_Ids = new ArrayList<>();

    public FirebaseService(DatabaseReference db){
        this.firebaseDB = db;
    }


    // And removing source on firebase then returning a boolean
    public Boolean save(Source source, ArrayList<String> saved_sourceIds){


        return saved;

    }

    // Read
    public FirebaseHelper retrieveSources(Source source){
        firebaseDB.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*firebaseHelper.clearSourceArrays();*/
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    // Adding firebase queried sources to source_objects
                    saved_Ids.add(Objects.requireNonNull(snapshot.getValue(Source.class))
                            .getSource_id());
                    source_objects.add(snapshot.getValue(Source.class));

                }

                if (!saved_Ids.contains(source.getSource_id())){
                    source.setSource_Uid(firebaseDB.push().getKey());
                    source.setSaved_status(true);
                    firebaseDB.child(source.getSource_Uid()).setValue(source);

                } else {
                    firebaseDB.child(source.getSource_Uid()).setValue(null);
                    source.setSaved_status(false);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return new FirebaseHelper(saved_Ids, source_objects);
    }


}
