package com.hai.jedi.newspie.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hai.jedi.newspie.Models.Source;

import java.util.List;

public class SourcesWrapper {

    @SerializedName("sources")
    @Expose
    private List<Source> source_list = null;

    public List<Source> getSource_list() {
        return source_list;
    }

    /*public List<>*/

}
