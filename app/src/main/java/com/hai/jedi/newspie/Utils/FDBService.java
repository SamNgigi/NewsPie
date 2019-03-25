package com.hai.jedi.newspie.Utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hai.jedi.newspie.Mapper.FirebaseMapper;

import java.util.List;



public abstract class FDBService<Model> {

    protected DatabaseReference databaseReference;
    protected FDBInterfaceCallBack<Model> fdbInterfaceCallBack;
    private BaseValueEventListener listener;
    private FirebaseMapper mapper;

    protected abstract String getRootNode();

    public FDBService(FirebaseMapper mapper){
        databaseReference = FirebaseDatabase.getInstance().getReference(getRootNode());
        this.mapper = mapper;
    }

    public void addListener(FDBInterfaceCallBack<Model> fdbCallBack){
        this.fdbInterfaceCallBack = fdbCallBack;
        listener = new BaseValueEventListener(mapper, fdbInterfaceCallBack);
        databaseReference.addValueEventListener(listener);
    }

    public void removeListener(){
        databaseReference.removeEventListener(listener);
    }


    public interface FDBInterfaceCallBack<T>{
        void onSuccess(List<T> result);

        void onError(Exception exception);
    }

}
