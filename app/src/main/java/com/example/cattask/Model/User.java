package com.example.cattask.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    public User()
    {

    }


    @SerializedName("firstname")
    public String firstname;
    @SerializedName("midname")
    public String midname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("confirm_password")
    public String confirm_password;
    @SerializedName("email")
    public String email;
    @SerializedName("mobile_number")
    public String mobile_number;
    @SerializedName("fk_gender_id")
    public String fkgenderid;
    @SerializedName("id")
    public String id;
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("image_profile")
    public String image_profile;
    @SerializedName("creation_date")
    public String creation_date;
    @SerializedName("update_date")
    public String update_date;
    @SerializedName("validity")
    public String validity;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getFkgenderid() {
        return fkgenderid;
    }

    public void setFkgenderid(String fkgenderid) {
        this.fkgenderid = fkgenderid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
