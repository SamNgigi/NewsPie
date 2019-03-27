package com.hai.jedi.newspie.ViewModel;

import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.Services.FBSources;
import com.hai.jedi.newspie.Utils.FDBAbstractClass;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirebaseViewModel extends ViewModel {

    private MutableLiveData<List<Source>> fbSources_list;
    private MutableLiveData<List<String>> fbSources_ids;
    private FBSources fbSources = new FBSources();

    public LiveData<List<Source>> getSources(){
        if(fbSources_list == null){
            fbSources_list = new MutableLiveData<>();
            loadFbSources();
        }
        return fbSources_list;
    }



    @Override
    protected void onCleared(){
        fbSources.removeListener();
    }


    private void loadFbSources(){
        fbSources.addListener(new FDBAbstractClass.FDBInterfaceCallBack<Source>() {
            @Override
            public void onSuccess(List<Source> result) {
                List<String> source_ids = new ArrayList<>();


                fbSources_list.setValue(result);
            }

            @Override
            public void onError(Exception exception) {
                fbSources_list.setValue(null);
            }
        });
    }


}
