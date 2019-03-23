package com.hai.jedi.newspie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    @Expose
    String source_id;
    @SerializedName("name")
    @Expose
    String source_name;
    @SerializedName("description")
    @Expose
    String source_description;
    @SerializedName("url")
    @Expose
    String source_url;
    @SerializedName("category")
    @Expose
    String source_category;
    String source_index;
    String source_Uid;
    Boolean saved_status;




    // Empty constructor for our Parceler;
    public Source(){}

    public Source(
            String id, String name, String description,
            String host_url, String category, String uid
    ) {
        this.source_id = id;
        this.source_name = name;
        this.source_description = description;
        this.source_url = host_url;
        this.source_category = category;
        this.source_index = "not_specified";
        this.source_Uid = uid;
        this.saved_status = null;
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

    public String getSource_Uid(){ return source_Uid; }

    public void setSource_Uid(String new_uid){
        this.source_Uid = new_uid;
    }

    public Boolean getSaved_status(){
        return saved_status;
    }

    public void setSaved_status(Boolean status){
        this.saved_status = status;
    }


}
