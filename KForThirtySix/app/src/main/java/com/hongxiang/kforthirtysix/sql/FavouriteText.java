package com.hongxiang.kforthirtysix.sql;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "FAVOURITE_TEXT".
 */
public class FavouriteText {

    private Long id;
    private String title;
    private String writer;
    private String urlid;

    public FavouriteText() {
    }

    public FavouriteText(Long id) {
        this.id = id;
    }

    public FavouriteText(Long id, String title, String writer, String urlid) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.urlid = urlid;
    }

    public FavouriteText(String writer, String title, String urlid) {
        this.writer = writer;
        this.title = title;
        this.urlid = urlid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getUrlid() {
        return urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid;
    }

}
