package com.hai.jedi.newspie.ViewModel;

import android.util.Log;

import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Models.NewsSource;
import com.hai.jedi.newspie.Services.SourcesWrapper;
import com.hai.jedi.newspie.Services.NewsPieInterface;
import com.hai.jedi.newspie.Services.NewsPieService;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceViewModel extends ViewModel {
    private static final String TAG = SourceViewModel.class.getSimpleName();

    private MutableLiveData<SourcesWrapper> sources_list;
    String default_country = "us";

    // Overriding the sourcesForCategory method
    public LiveData<SourcesWrapper> sourcesForCategory(String category){
        if(sources_list == null){
            sources_list = new MutableLiveData<>();
            loadSources4Category(category);
        }
        return sources_list;
    }

    // Overriding the loadSources4Category method
    private void loadSources4Category(String category){
        loadSources4Category(default_country, category);
    }


    public LiveData<SourcesWrapper> sourcesForCategory(String country_id, String category){
        if(sources_list == null){
            sources_list = new MutableLiveData<>();
            loadSources4Category(country_id, category);
        }
        return sources_list;
    }


    private void loadSources4Category(String country, String category){
        NewsPieInterface newsApiCall = NewsPieService.newApiCall();
        Call<SourcesWrapper> call = newsApiCall.getNewsSources(
                country, category, Constants.NEWS_API_KEY);

        call.enqueue(new Callback<SourcesWrapper>(){
            @Override
            public void onResponse(@NonNull Call<SourcesWrapper> call,
                                   @NonNull Response<SourcesWrapper> response){
                if(response.isSuccessful()){
                    sources_list.setValue(response.body());
                    Log.d(TAG, String.valueOf(Objects.requireNonNull(response.body()).getSource_list()));
                }
            }
            @Override
            public void onFailure(@NonNull Call<SourcesWrapper> call,
                                  @NonNull Throwable exception){
                exception.printStackTrace();
            }

        });

    }

}