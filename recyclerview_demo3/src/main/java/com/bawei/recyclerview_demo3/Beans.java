package com.bawei.recyclerview_demo3;

import java.io.Serializable;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/12 0012
 */

public class Beans implements Serializable{

    private String title;
    private boolean tag;
    private boolean isV;

    public Beans(String title, boolean tag, boolean isV) {
        this.title = title;
        this.tag = tag;
        this.isV = isV;
    }

    public boolean isV() {
        return isV;
    }

    public void setV(boolean v) {
        isV = v;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }


    @Override
    public String toString() {
        return "Beans{" +
                "title='" + title + '\'' +
                ", tag=" + tag +
                '}';
    }
}
