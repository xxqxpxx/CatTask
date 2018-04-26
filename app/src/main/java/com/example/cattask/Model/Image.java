package com.example.cattask.Model;

import com.google.gson.annotations.SerializedName;

public class Image {
    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    @SerializedName("image_profile")
    public String image_profile;

}
