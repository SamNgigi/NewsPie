package com.hai.jedi.newspie.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private static final String TAG = SharedViewModel.class.getSimpleName().toUpperCase();

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



}
