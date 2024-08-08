package com.app.myandroidtask.models;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myandroidtask.databinding.UserItemBinding;


public class UserListViewHolder extends RecyclerView.ViewHolder {
    UserItemBinding b;

    public UserListViewHolder(@NonNull View itemView, UserItemBinding binding) {
        super(itemView);
        this.b = binding;
    }

    public void bind(ProfileModel model, int position, UserActionListener carActionListener) {

        b.tvName.setText(model.name);
        b.tvEmail.setText(model.email);
        b.tvAdharCard.setText(model.adhar);
        b.tvDateOfBirth.setText(model.dateOfBirth);
        b.tvGender.setText(model.dateOfJoining);


    }
    public static UserListViewHolder create(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        UserItemBinding b = UserItemBinding.inflate(inflater, parent, false);
        return new UserListViewHolder(b.getRoot(), b);
    }
}

