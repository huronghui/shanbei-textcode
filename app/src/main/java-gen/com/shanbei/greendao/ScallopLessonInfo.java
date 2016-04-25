package com.shanbei.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ScallopLessonCache".
 */
public class ScallopLessonInfo {

    private Long id;
    /** Not-null value. */
    private String UnitBelong;
    /** Not-null value. */
    private String LessonList;
    /** Not-null value. */
    private String content;
    /** Not-null value. */
    private String title;
    /** Not-null value. */
    private String info;

    public ScallopLessonInfo() {
    }

    public ScallopLessonInfo(Long id) {
        this.id = id;
    }

    public ScallopLessonInfo(Long id, String UnitBelong, String LessonList, String content, String title, String info) {
        this.id = id;
        this.UnitBelong = UnitBelong;
        this.LessonList = LessonList;
        this.content = content;
        this.title = title;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getUnitBelong() {
        return UnitBelong;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUnitBelong(String UnitBelong) {
        this.UnitBelong = UnitBelong;
    }

    /** Not-null value. */
    public String getLessonList() {
        return LessonList;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLessonList(String LessonList) {
        this.LessonList = LessonList;
    }

    /** Not-null value. */
    public String getContent() {
        return content;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContent(String content) {
        this.content = content;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Not-null value. */
    public String getInfo() {
        return info;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setInfo(String info) {
        this.info = info;
    }

}
