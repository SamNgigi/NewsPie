package com.hai.jedi.newspie.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private static final String TAG = SharedViewModel.class.getSimpleName().toUpperCase();


    // CATEGORY SOURCE MANENOS
    private MutableLiveData<String> selected_category = new MutableLiveData<>();

    // Overriding the setSelected_category to have a default value
    public void setSelected_category(){
        String default_category = "general";
        setSelected_category(default_category);
    }

    public void setSelected_category(String selected){
        selected_category.setValue(selected.toLowerCase());
    }

    public LiveData<String> getSelected_category(){
        return selected_category;
    }


    // SOURCE_ID HEADLINES MANENOS
    private MutableLiveData<String> selected_sourceId = new MutableLiveData<>();

    // Overriding the setSelected_sourceId to have a default value
    public void setSelected_sourceId(){
        String default_source = "bbc-news";
        setSelected_sourceId(default_source);
    }

    public void setSelected_sourceId(String selected_source){
        selected_sourceId.setValue(selected_source);
    }

    public LiveData<String> getSelected_source(){ return selected_sourceId; }



}
