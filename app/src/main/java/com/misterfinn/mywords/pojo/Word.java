package com.misterfinn.mywords.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {

    @Ignore
    public Word(String englishWord, String translation, String partOfSpeech, String tag) {
        this.englishWord = englishWord;
        Translation = translation;
        this.partOfSpeech = partOfSpeech;
        this.tag = tag;
    }
    public Word(){
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String englishWord;
    private String Translation;
    private String partOfSpeech;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getTranslation() {
        return Translation;
    }

    public void setTranslation(String translation) {
        Translation = translation;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}


