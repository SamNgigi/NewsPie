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
    ArrayList<String> saved_Ids = new ArrayList<>();

    public FirebaseService(DatabaseReference db){
        this.firebaseDB = db;
    }

    public void addSource(Source source){
        source.setSource_Uid(firebaseDB.push().getKey());
        source.setSaved_status(true);
        firebaseDB.child(source.getSource_Uid()).setValue(source);
    }

    public void removeSource(Source source){
        source.setSaved_status(false);
        firebaseDB.child(source.getSource_Uid()).removeValue();
    }


}
