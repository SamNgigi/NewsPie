package com.hai.jedi.newspie.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hai.jedi.newspie.Models.NewsSource;

import java.util.List;

public class SourcesWrapper {

    @SerializedName("sources")
    @Expose
    private List<NewsSource> source_list = null;

    public List<NewsSource> getSource_list() {
        return source_list;
    }

}
