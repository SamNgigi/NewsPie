package com.hai.jedi.newspie.ViewModel;


import com.hai.jedi.newspie.Models.Source;

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
    private MutableLiveData<Source> selected_source = new MutableLiveData<>();


    // Overloading the setSelected_sourceId to have a default value
    public void setSelected_sourceId(){
        String default_source = "bbc-news";
        selected_sourceId.setValue(default_source);
    }

    public void setSelected_sourceId(String selected_source){
        selected_sourceId.setValue(selected_source);
    }

    public void setSelected_source(Source source){
        selected_source.setValue(source);
    }

    public LiveData<String> getSelected_sourceId(){ return selected_sourceId; }
    public LiveData<Source> getSelected_source(){return selected_source;}


    /*FIREBASE SOURCE QUERY*//*
    private MutableLiveData<ArrayList<String>> fbSourceId_list;
    private MutableLiveData<SourcesWrapper> fbSource_list;
    private DatabaseReference fbReference;
    private FirebaseService fbService;
    private FirebaseHelper fbHelper;

    public LiveData<ArrayList<String>> getFbSourceIds(){

        if(fbSourceId_list == null){
            fbSourceId_list = new MutableLiveData<>();
            *//*loadFbSourceIdList(source);*//*
        }
        return fbSourceId_list;
    }

    public void loadFbSourceIdList(Source source){

        fbReference = FirebaseDatabase.getInstance()
                                        .getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
        fbService = new FirebaseService(fbReference);
        fbSourceId_list.setValue(
                fbService.retrieveSources(source).source_ids
        );


    }*/

    /*public LiveData<SourcesWrapper> getFbSources(){

        if(fbSource_list == null){
            fbSource_list = new MutableLiveData<>();
            *//*loadFbSourceList();*//*
        }
        return fbSource_list;
    }

    public void loadFbSourceList(Source source){

        fbReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_SOURCE_BOOKMARKS);
        fbService = new FirebaseService(fbReference);
        fbSource_list.setValue(
                fbService.retrieveSources(source).sources
        );


    }*/

}
