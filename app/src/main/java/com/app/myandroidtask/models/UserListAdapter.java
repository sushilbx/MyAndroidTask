package com.app.myandroidtask.models;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class UserListAdapter extends ListAdapter<ProfileModel, UserListViewHolder> {
    UserActionListener carActionListener;
    public UserListAdapter(@NonNull DiffUtil.ItemCallback<ProfileModel> diffCallback, UserActionListener carActionListener) {
        super(diffCallback);
        this.carActionListener = carActionListener;
    }
    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserListViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        holder.bind(getItem(position), position, carActionListener);

    }

}


