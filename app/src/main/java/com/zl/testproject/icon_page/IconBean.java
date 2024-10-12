package com.zl.testproject.icon_page;

import java.io.Serializable;

public class IconBean implements Serializable {

    private String path;
    private String title;
    private int res;

    public IconBean(String title, int res) {
        this.title = title;
        this.res = res;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
