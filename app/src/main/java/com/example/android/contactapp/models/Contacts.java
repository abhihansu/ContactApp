package com.example.android.contactapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {
    private String name;
    private String phoneNo;
    private String device;
    private String email;
    private String profileImage;

    public Contacts(String name, String phoneNo, String device, String email, String profileImage) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.device = device;
        this.email = email;
        this.profileImage = profileImage;
    }

    protected Contacts(Parcel in) {
        name = in.readString();
        phoneNo = in.readString();
        device = in.readString();
        email = in.readString();
        profileImage = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", device='" + device + '\'' +
                ", email='" + email + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNo);
        dest.writeString(device);
        dest.writeString(email);
        dest.writeString(profileImage);
    }
}

