package com.hai.jedi.newspie.Utils;

import android.content.Entity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hai.jedi.newspie.Mappers.FirebaseMapper;
import com.hai.jedi.newspie.Services.FirebaseService;

import java.util.List;

public class BaseValueEventListener<Model, Entity> implements ValueEventListener {

    private FirebaseMapper<Entity, Model> fbMapper;
    private FirebaseService.FirebaseInterfaceCallback<Model> fsCallback;

    public BaseValueEventListener(FirebaseMapper<Entity, Model> mapper,
                                  FirebaseService.FirebaseInterfaceCallback<Model> callback){
        this.fbMapper = mapper;
        this.fsCallback = callback;

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot){
        List<Model> data = fbMapper.mapList(dataSnapshot);
        fsCallback.onSuccess(data);
    }

    @Override
    public void onCancelled(DatabaseError dbError){
        fsCallback.onError(dbError.toException());
    }

}
