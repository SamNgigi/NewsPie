package com.hai.jedi.newspie.Services;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Mappers.FirebaseMapper;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.Utils.BaseValueEventListener;

import java.util.ArrayList;
import java.util.List;


public abstract class FirebaseService<Model> {
    public final String TAG = FirebaseService.class.getSimpleName().toUpperCase();

    protected DatabaseReference firebaseDb;
    protected FirebaseInterfaceCallback<Model> fbInterfaceCallback;
    private BaseValueEventListener baseListener;
    private FirebaseMapper mapper;

    protected abstract String getRootNode();

    public FirebaseService(FirebaseMapper mapper){
        firebaseDb = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
        this.mapper = mapper;
    }

    public void addListener(FirebaseInterfaceCallback<Model> fbCallback){
        this.fbInterfaceCallback = fbCallback;
        baseListener = new BaseValueEventListener(mapper, fbInterfaceCallback);
        firebaseDb.addValueEventListener(baseListener);
    }

    public void removeListener(){
        firebaseDb.removeEventListener(baseListener);
    }

    public interface FirebaseInterfaceCallback<T>{
        void onSuccess(List<T> result);

        void onError(Exception exception);
    }


}
