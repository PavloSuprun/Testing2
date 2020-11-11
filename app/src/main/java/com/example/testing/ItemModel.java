package com.example.testing;

import android.animation.AnimatorSet;
import android.widget.TextView;

public class ItemModel {
    //private int image;
    private String word, transcription, translation;
    private long id;

    public ItemModel() {
    }

    public ItemModel(/*int image,*/long id, String word, /*String transcription,*/ String translation) {
        //this.image = image;
        this.id = id;
        this.word = word;
//        this.transcription = transcription;
        this.translation = translation;
    }
    public ItemModel(/*int image,*/ String word, String transcription, String translation) {
        //this.image = image;
        this.id = id;
        this.word = word;
        this.transcription = transcription;
        this.translation = translation;
    }

    public long getId() {
        return id;
    }
    //public int getImage() {
    //    return image;
    //}

    public String getWord() {
        return word;
    }

    public String getTranscription() {
        return transcription;
    }

    public String getTranslation() {
        return translation;
    }
    @Override
    public String toString() {
        return this.word + " : " + this.translation;
    }
}

