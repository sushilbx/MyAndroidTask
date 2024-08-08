package com.app.myandroidtask;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.myandroidtask.models.ProfileModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SessionManager {
    public static final String KEY_USER_DETAILS = "user_details";
    private static final String PREF_NAME = "userData";
    private static final String IS_LOGIN = "isLogin";
    public static final String KEY_USER_LIST = "user_list";

    private static SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserDetails(ProfileModel profileModel) {
        editor.putString(KEY_USER_DETAILS, new Gson().toJson(profileModel));
        editor.commit();
    }
    public void setUserList(List<ProfileModel> list) {
        Log.e("response", new Gson().toJson(list));
        editor.putString(KEY_USER_LIST, new Gson().toJson(list));
        editor.commit();
    }
    public List<ProfileModel> getUserList() {
        Type carListType = new TypeToken<List<ProfileModel>>() {}.getType();
        return new Gson().fromJson(pref.getString(KEY_USER_LIST, "[]"), carListType);
    }
    public void clearSession() {
        editor.clear();
        editor.commit();

    }

    public ProfileModel getUser() {
        return new Gson().fromJson(pref.getString(KEY_USER_DETAILS, null), ProfileModel.class);
    }

    public boolean isLoggedIn() {

        return pref.getBoolean(IS_LOGIN, false);
    }
}









