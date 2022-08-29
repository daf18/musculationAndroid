package com.example.musculaction.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercice implements Parcelable {
    private int id;
    private String title;
    private int img;
    private String description;
    private String details;
    private String youtubeUrl;
    private int category;

    public Exercice(int id, String title, int img, String description, String details, String youtubeUrl,int category) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.details = details;
        this.youtubeUrl = youtubeUrl;
        this.category = category;
    }
    public Exercice(String title, int img, String description, String details, String youtubeUrl,int category) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.details = details;
        this.youtubeUrl = youtubeUrl;
        this.category = category;
    }

    public Exercice() {
    }

    protected Exercice(Parcel in) {
        id = in.readInt();
        title = in.readString();
        img = in.readInt();
        description = in.readString();
        details = in.readString();
        youtubeUrl = in.readString();
        category = in.readInt();
    }

    public static final Creator<Exercice> CREATOR = new Creator<Exercice>() {
        @Override
        public Exercice createFromParcel(Parcel in) {
            return new Exercice(in);
        }

        @Override
        public Exercice[] newArray(int size) {
            return new Exercice[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(img);
        parcel.writeString(description);
        parcel.writeString(details);
        parcel.writeString(youtubeUrl);
        parcel.writeInt(category);
    }
}
