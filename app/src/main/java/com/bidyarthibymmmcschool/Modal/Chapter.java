package com.bidyarthibymmmcschool.Modal;

public class Chapter {


    private String chapter;
    private String title;

    public Chapter() {

    }

    public Chapter(String chapter, String title) {
        this.chapter = chapter;
        this.title = title;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
