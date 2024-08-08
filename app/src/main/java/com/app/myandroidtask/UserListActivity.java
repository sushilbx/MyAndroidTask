package com.app.myandroidtask;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.myandroidtask.databinding.ActivityUserListBinding;
import com.app.myandroidtask.models.ProfileModel;
import com.app.myandroidtask.models.UserActionListener;
import com.app.myandroidtask.models.UserListAdapter;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ActivityUserListBinding b;
    SessionManager sessionManger;
    UserListAdapter userListAdapter;
    List<ProfileModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityUserListBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        sessionManger = new SessionManager(this);
        userListAdapter = new UserListAdapter(ProfileModel.ADDRESS_COMPARATOR, new UserActionListener() {
            @Override
            public void onDelete(int position) {
                deleteCar(position);

            }

            @Override
            public void onUpdate(int position) {

            }
        });
        list =sessionManger.getUserList();
        userListAdapter.submitList(list);
        b.rvUserList.setAdapter(userListAdapter);



    }

    private void deleteCar(int position) {
        list.remove(position);
        sessionManger.setUserList(list);
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        userListAdapter.notifyDataSetChanged();
    }
}