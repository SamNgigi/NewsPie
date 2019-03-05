package com.hai.jedi.newspie.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsPieInterface {

    @GET("sources?language=en")
    Call<SourcesWrapper> getNewsSources(@Query("category") String category,
                                        @Query("apiKey") String apiKey);
    @GET("top-headlines")
    Call<HeadlineWrapper> getSourceHeadlines(@Query("sources") String source,
                                             @Query("apiKey") String apiKey);
}
