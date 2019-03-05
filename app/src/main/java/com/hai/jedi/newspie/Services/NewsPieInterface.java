package com.hai.jedi.newspie.Services;

import com.hai.jedi.newspie.Models.NewsSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsPieInterface {

    @GET("sources?language=en")
    Call<SourcesWrapper> getNewsSources(@Query("category") String category,
                                        @Query("apiKey") String apiKey);

}
