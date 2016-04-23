package com.example.hrh.shanybe_text.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hrh on 2016/3/10.
 */
public class MainMenuModel implements Parcelable{

    public MainMenuModel(String text) {
        this.text = text;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    protected MainMenuModel(Parcel in) {
        this.text = in.readString();
    }

    public static final Creator<MainMenuModel> CREATOR = new Creator<MainMenuModel>() {
        public MainMenuModel createFromParcel(Parcel source) {
            return new MainMenuModel(source);
        }

        public MainMenuModel[] newArray(int size) {
            return new MainMenuModel[size];
        }
    };
}
