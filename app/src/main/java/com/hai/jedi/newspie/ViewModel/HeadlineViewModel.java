package com.hai.jedi.newspie.ViewModel;

import android.util.Log;

import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Services.NewsPieInterface;
import com.hai.jedi.newspie.Services.HeadlineWrapper;
import com.hai.jedi.newspie.Services.NewsPieService;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadlineViewModel extends ViewModel{

    private static final String TAG = HeadlineViewModel.class
                                                        .getSimpleName()
                                                        .toUpperCase();

    private MutableLiveData<HeadlineWrapper> article_list;

    public LiveData<HeadlineWrapper> sourceHeadlines(){
        if(article_list == null){
            article_list = new MutableLiveData<>();
            loadHeadlines4Source();
        }
        return article_list;
    }


    private void loadHeadlines4Source() {
        String default_source = "bbc-news";
        loadHeadlines4Source(default_source);}


    public LiveData<HeadlineWrapper> sourceHeadlines(String source_id){
        if(article_list == null){
            article_list = new MutableLiveData<>();
           // loadHeadlines4Source(source_id);
        }
        return article_list;
    }

    public void loadHeadlines4Source(String source_id){
        NewsPieInterface newsApiCall = NewsPieService.newApiCall();
        Call<HeadlineWrapper> call = newsApiCall.getSourceHeadlines(source_id, Constants.NEWS_API_KEY);

        call.enqueue(new Callback<HeadlineWrapper>() {
            @Override
            public void onResponse(@NonNull Call<HeadlineWrapper> call,
                                   @NonNull Response<HeadlineWrapper> response) {
                if(response.isSuccessful()){
                    article_list.setValue(response.body());
                    Log.d(TAG, String.valueOf(Objects.requireNonNull(response.body())
                                                     .getArticles()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeadlineWrapper> call,
                                  @NonNull Throwable exception) {
                exception.printStackTrace();
            }
        });

    }

}
