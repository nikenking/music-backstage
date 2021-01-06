package com.zhengchuang.music.domain;

import java.io.Serializable;

public class SongList implements Serializable {
    private Integer id;
    private String pic;
    private String title;    /*歌单标题*/
    private String introduction;
    private String style;//该歌单风格：轻快，欧美，热榜，流行，日语，纯音乐。。。

    public SongList() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}

