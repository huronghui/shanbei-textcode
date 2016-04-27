package com.example.hrh.shanybe_text.domain.letter;

/**
 * Created by hrh on 2016/4/23.
 */
public class LetterBean {

    private String title;
    private String englishContent;
    private String chineseContent;

    public LetterBean(String title, String englishContent, String chineseContent) {
        this.title = title;
        this.englishContent = englishContent;
        this.chineseContent = chineseContent;
    }

    public LetterBean( String englishContent) {
//        this.title = title;
        this.englishContent = englishContent;
//        this.chineseContent = chineseContent;
    }

    public String getTitle() {
        return title;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public String getChineseContent() {
        return chineseContent;
    }
}
