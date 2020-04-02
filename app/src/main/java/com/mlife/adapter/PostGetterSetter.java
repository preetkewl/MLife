package com.mlife.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 9/8/2017.
 */

public class PostGetterSetter {

    int id;
    String Heading, Date, Detail, LikeCount, CommentCount;
    List<ProjectImageGetterSetter> list = new ArrayList<>();

    public PostGetterSetter(int id, String heading, String date, String detail, String likeCount, String commentCount, List<ProjectImageGetterSetter> list) {
        this.id = id;
        Heading = heading;
        Date = date;
        Detail = detail;
        LikeCount = likeCount;
        CommentCount = commentCount;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(String likeCount) {
        LikeCount = likeCount;
    }

    public String getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(String commentCount) {
        CommentCount = commentCount;
    }

    public List<ProjectImageGetterSetter> getList() {
        return list;
    }

    public void setList(List<ProjectImageGetterSetter> list) {
        this.list = list;
    }
}
