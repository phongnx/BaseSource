package com.base.data.network;


import com.base.data.local.preference.PreferencesHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Phong on 11/9/2016.
 */

public class DataManager {
    private RemoteApiService mRemoteApiService;
    private PreferencesHelper mPreferencesHelper;

    public DataManager(RemoteApiService remoteApiService, PreferencesHelper preferencesHelper) {
        this.mRemoteApiService = remoteApiService;
        this.mPreferencesHelper = preferencesHelper;
    }

    public Call<ResponseBody> login(String email, String password, String android_push_key) {
        return mRemoteApiService.login(email, password, android_push_key);
    }

    public Call<ResponseBody> register(String full_name, String email, String password, String android_push_key, File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return mRemoteApiService.register(full_name, email, password, android_push_key, multipartBody);
    }
}
