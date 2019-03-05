package com.hai.jedi.newspie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    @Expose
    private String source_id;
    @SerializedName("name")
    @Expose
    private String source_name;
    private String source_description;
    private String source_url;
    @SerializedName("category")
    @Expose
    private String source_category;
    private String source_index;




    // Empty constructor for our Parceler;
    public Source(){}

    public Source(
            String id, String name, String description,
            String host_url, String category
    ) {
        this.source_id = id;
        this.source_name = name;
        this.source_description = description;
        this.source_url = host_url;
        this.source_category = category;
        this.source_index = "not_specified";
    }

    // Our getters & setters
    public String getSource_id(){
        return source_id;
    }

    public void setSource_id(String id){
        this.source_id = id;
    }

    public String getSource_name(){
        return source_name;
    }

    public String getSource_description(){
        return source_description;
    }

    public String getSource_url(){
        return source_url;
    }

    public String getSource_category(){
        return source_category;
    }

    public void setSource_category(String category){
        this.source_category = category;
    }

    public String getSource_index(){ return source_index; }

    public void setSource_index(String new_index){
        this.source_index = new_index;
    }
}
