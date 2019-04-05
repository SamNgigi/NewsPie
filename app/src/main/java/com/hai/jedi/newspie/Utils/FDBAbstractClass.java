package com.hai.jedi.newspie.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hai.jedi.newspie.Mapper.FirebaseMapper;

import java.util.List;
import java.util.Objects;


/**
 * This abstract class allows us to make an asynchronous call in our view models and pass any model
 * */
public abstract class FDBAbstractClass<Model> {

    protected DatabaseReference databaseReference;
    protected FDBInterfaceCallBack<Model> fdbInterfaceCallBack;
    private BaseValueEventListener listener;
    private FirebaseMapper mapper;

    protected abstract String getRootNode();

    /**
     * We create a constructor with our mapper that converts a datasnapshot to the object we desire
     * */
    public FDBAbstractClass(FirebaseMapper mapper){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid = Objects.requireNonNull(user).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(getRootNode()).child(user_uid);
        this.mapper = mapper;
    }

    /**
     * We create a function that takes in a call back of the model we expect back
     * */
    public void addListener(FDBInterfaceCallBack<Model> fdbCallBack){
        this.fdbInterfaceCallBack = fdbCallBack;
        // Our listener that will map our data snapshot to our object
        listener = new BaseValueEventListener(mapper, fdbInterfaceCallBack);
        // Adding our passing our listener the reference method
        databaseReference.addValueEventListener(listener);
    }
    // removing the our listener
    public void removeListener(){
        databaseReference.removeEventListener(listener);
    }

    // Defining our call back interface with our success and on error function
    public interface FDBInterfaceCallBack<T>{
        void onSuccess(List<T> result);

        void onError(Exception exception);
    }

}
