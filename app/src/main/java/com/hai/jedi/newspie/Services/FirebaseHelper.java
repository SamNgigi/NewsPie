package com.hai.jedi.newspie.Services;

import com.hai.jedi.newspie.Models.Source;

import java.util.ArrayList;

public class FirebaseHelper {
    public ArrayList<String> source_ids;
    public ArrayList<Source> sources;

    public FirebaseHelper(ArrayList<String> ids, ArrayList<Source> s){
        this.source_ids = ids;
        this.sources = s;
    }

    public ArrayList<String> getSource_ids (){
        return source_ids;
    }

    public void setSource_ids(ArrayList<String> source_ids){
        this.source_ids = source_ids;
    }



    public void clearSourceArrays(){
        this.source_ids.clear();
        this.sources.clear();
    }


}
