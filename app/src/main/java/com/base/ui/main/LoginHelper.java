package com.base.ui.main;

import android.os.AsyncTask;

import com.base.data.ApplicationModules;
import com.base.data.network.DataManager;
import com.base.data.local.preference.PreferencesHelper;
import com.base.data.network.ApiResult;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Phong on 3/1/2017.
 */

public class LoginHelper {
    private DataManager mDataManager;
    private PreferencesHelper mPreferencesHelper;
    private ApiResult mApiResult;

    public LoginHelper(ApiResult apiResult) {
        mApiResult = apiResult;
        mPreferencesHelper = ApplicationModules.getInstant().getPreferencesHelper();
        mDataManager = ApplicationModules.getInstant().getDataManager();
    }

    public void login(String email, String password, String push_key) {
        class AsyncTaskProcessData extends AsyncTask<String, Void, Object[]> {
            @Override
            protected Object[] doInBackground(String... params) {
                Object[] results = new Object[3];

                boolean isSuccess = false;
                String message = "";
                String resultString = params[0]; // result string from server

                try {
                    JSONObject object = new JSONObject(resultString);
                    int code = object.getInt("code");
                    if (code == 0) {
                        isSuccess = true;
                    }
                    if (object.has("message")) {
                        message = object.getString("message");
                    }
                } catch (Exception e) {
                }

                mPreferencesHelper.saveUserId("user_id"); // Save user_id or somethings you want to SharedPreferences

                results[0] = isSuccess;
                results[1] = message;
                results[2] = resultString;
                return results;
            }

            @Override
            protected void onPostExecute(Object[] objects) {
                super.onPostExecute(objects);
                if (mApiResult != null) {
                    if ((Boolean) objects[0]) {
                        mApiResult.onSuccess("");
                    } else {
                        mApiResult.onError(String.valueOf(objects[1]));
                    }
                }
            }
        }

        Call<ResponseBody> call = mDataManager.login(email, password, push_key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string().toString();
                        new AsyncTaskProcessData().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, result);
                    } catch (Exception e) {
                    }
                } else {
                    if (mApiResult != null) {
                        mApiResult.onError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                if (mApiResult != null) {
                    mApiResult.onError(throwable.getMessage());
                }
            }
        });
    }
}
