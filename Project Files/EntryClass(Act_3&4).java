package com.example.loginscreen;

import android.os.Parcel;
import android.os.Parcelable;

public class EntryClass implements Parcelable {
    private int image;
 private String name;
 private String remark;
 private String birthday;
 private String gender;
 private String address;
 private String contact;
 private String hobbies;
 private String otherInfo;

    public EntryClass(int image, String name, String remark, String birthday, String gender, String address, String contact, String hobbies, String otherInfo) {
        this.image = image;
        this.name = name;
        this.remark = remark;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.hobbies = hobbies;
        this.otherInfo = otherInfo;
    }

    protected EntryClass(Parcel in) {
        image = in.readInt();
        name = in.readString();
        remark = in.readString();
        birthday = in.readString();
        gender = in.readString();
        address = in.readString();
        contact = in.readString();
        hobbies = in.readString();
        otherInfo = in.readString();
    }

    public static final Creator<EntryClass> CREATOR = new Creator<EntryClass>() {
        @Override
        public EntryClass createFromParcel(Parcel in) {
            return new EntryClass(in);
        }

        @Override
        public EntryClass[] newArray(int size) {
            return new EntryClass[size];
        }
    };

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(remark);
        dest.writeString(birthday);
        dest.writeString(gender);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeString(hobbies);
        dest.writeString(otherInfo);
    }
}
