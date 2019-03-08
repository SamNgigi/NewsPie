package com.hai.jedi.newspie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {
    @SerializedName("source")
    @Expose
    private Source baseSource;
    private String author;
    @SerializedName("title")
    @Expose
    private String title;
    private String description;
    private String article_url;
    @SerializedName("urlToImage")
    @Expose
    private String img_url;
    @SerializedName("publishedAt")
    @Expose
    private String published_at;
    private String content;

    public Headline(){}

    public Headline(Source source, String author, String title,
                    String description, String article_url, String image,
                    String published_at, String content){
        this.baseSource = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.article_url = article_url;
        this.img_url = image;
        this.published_at = published_at;
        this.content = content;
    }

    public Source getSource(){
        return baseSource;
    }

    public void setSource (Source source){
        this.baseSource = source;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getArticle_url(){
        return article_url;
    }

    public String getImg_url(){
        return img_url;
    }

    public String getPublished_at(){
        return published_at;
    }

    public String getContent(){
        return content;
    }

}
