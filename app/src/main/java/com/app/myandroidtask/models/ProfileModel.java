package com.app.myandroidtask.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.Gson;

public class ProfileModel {
    public Long id;
    public String name;
    public String email;
    public String adhar;
    public String gender;
    public String dateOfJoining;
    public String dateOfBirth;

    public ProfileModel(Long id, String name, String email, String adhar, String gender, String dateOfJoining,String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adhar = adhar;
        this.gender = gender;
        this.dateOfJoining = dateOfJoining;
        this.dateOfBirth = dateOfBirth;
    }
    public static DiffUtil.ItemCallback<ProfileModel> ADDRESS_COMPARATOR = new DiffUtil.ItemCallback<ProfileModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProfileModel oldItem, @NonNull ProfileModel newItem) {
            return oldItem.name.equals(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProfileModel oldItem, @NonNull ProfileModel newItem) {
            return new Gson().toJson(oldItem).equals(new Gson().toJson(newItem));
        }
    };

}


