package com.hai.jedi.newspie.Utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hai.jedi.newspie.Mapper.FirebaseMapper;

import java.util.List;

/**
 * We use this class to query from firebase, then return a list with the appropriate model inside.
 * */
public class BaseValueEventListener<Entity, Model> implements ValueEventListener {

    private FirebaseMapper<Entity, Model> firebaseMapper;
    private FDBAbstractClass.FDBInterfaceCallBack<Model> callBack;


    public BaseValueEventListener(FirebaseMapper<Entity, Model> mapper,
                                  FDBAbstractClass.FDBInterfaceCallBack<Model> callBack){
        this.firebaseMapper = mapper;
        this.callBack = callBack;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot){
        List<Model> data = firebaseMapper.mapList(dataSnapshot);
        callBack.onSuccess(data);
    }

    @Override
    public void onCancelled(DatabaseError databaseError){
        callBack.onError(databaseError.toException());
    }

}
