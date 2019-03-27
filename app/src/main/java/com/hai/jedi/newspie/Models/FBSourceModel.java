package com.hai.jedi.newspie.Models;

public class FBSourceModel {



    String source_id;
    private String source_name;
    private String source_description;
    private String source_url;
    private String source_category;
    private String source_index;
    private String source_Uid;
    private boolean saved_status;


    /**
     * This class represents the data as received from firebase which is distinctly different from the
     * one from our articles api.
     *
     * We use to set our saved status and our source uid.
     * */

    // Empty constructor for our Parceler;
    public FBSourceModel(){}

    public FBSourceModel(
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
        this.saved_status = false;
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

    public void setSource_name(String name){
        this.source_name = name;
    }

    public String getSource_description(){
        return source_description;
    }

    public void setSource_description(String description){
        this.source_description = description;
    }

    public String getSource_url(){
        return source_url;
    }

    public void setSource_url(String url){
        this.source_url = url;
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

    public void setSaved_status(boolean status){
        this.saved_status = status;
    }



}
