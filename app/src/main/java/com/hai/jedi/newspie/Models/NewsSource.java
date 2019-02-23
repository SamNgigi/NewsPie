package com.hai.jedi.newspie.Models;

public class NewsSource {
    String source_id;
    String source_name;
    String source_description;
    String source_url;
    String source_category;
    String source_country;
    String source_index;

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

    // Our getters
    public String getSource_id(){
        return source_id;
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

    public String getSource_country(){
        return source_country;
    }

    public String getSource_index(){
        return source_index;
    }

    public void setSource_index(String new_index){
        this.source_index = new_index;
    }
}
