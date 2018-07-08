package com.example.alexk.studentprofilecreator;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private String name;
    private String email;
    private String department;
    private String mood;
    private byte[] avatar;

    public Profile(String name, String email, String department, String mood, byte[] avatar) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.avatar = avatar;
    }

    protected Profile(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.department = in.readString();
        this.mood = in.readString();
        this.avatar = in.createByteArray();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.department);
        dest.writeString(this.mood);
        dest.writeByteArray(avatar);

    }
}
