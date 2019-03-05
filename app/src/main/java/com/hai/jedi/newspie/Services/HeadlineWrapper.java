package com.hai.jedi.newspie.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hai.jedi.newspie.Models.Headline;

import java.util.List;

public class HeadlineWrapper {

    @SerializedName("articles")
    @Expose
    private List<Headline> articles = null;


    public List<Headline> getArticles() {
        return articles;
    }

}
