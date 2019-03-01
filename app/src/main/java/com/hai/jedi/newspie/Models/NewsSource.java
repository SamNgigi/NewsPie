package com.hai.jedi.newspie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsSource {

    @SerializedName("id")
    @Expose
    private String source_id;
    private String source_name;
    private String source_description;
    private String source_url;
    @SerializedName("category")
    @Expose
    private String source_category;
    @SerializedName("country")
    @Expose
    private String source_country;
    private String source_index;




    // Empty constructor for our Parceler;
    public NewsSource(){}

    public NewsSource(
            String id, String name, String description,
            String host_url, String category, String country, String index
    ) {
        this.source_id = id;
        this.source_name = name;
        this.source_description = description;
        this.source_url = host_url;
        this.source_category = category;
        this.source_country = country;
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

    public String getSource_country(){ return source_country; }
    public String getSource_index(){ return source_index; }

    public void setSource_index(String new_index){
        this.source_index = new_index;
    }
}
