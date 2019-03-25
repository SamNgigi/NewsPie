package com.hai.jedi.newspie.Utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hai.jedi.newspie.Mapper.FirebaseMapper;

import java.util.List;

public class BaseValueEventListener<Entity, Model> implements ValueEventListener {

    private FirebaseMapper<Entity, Model> firebaseMapper;
    private FDBService.FDBInterfaceCallBack<Model> callBack;

    public BaseValueEventListener(FirebaseMapper<Entity, Model> mapper,
                                  FDBService.FDBInterfaceCallBack<Model> callBack){
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
