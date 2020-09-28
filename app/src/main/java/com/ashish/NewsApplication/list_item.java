package com.ashish.NewsApplication;

public class list_item {

    private String imageUrl;
    private String title;
    private String details;
    private String content;
    private String newsUrl;

    public list_item(String imageUrl, String title, String details, String content, String newsUrl) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.details = details;
        this.content = content;
        this.newsUrl = newsUrl;
    }

    //getters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //setters
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
