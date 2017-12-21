package com.base.data.remote.helper;

import android.database.Observable;
import android.os.AsyncTask;

import com.base.data.ApplicationModules;
import com.base.data.models.User;
import com.base.data.remote.DataManager;
import com.base.data.local.preference.PreferencesHelper;
import com.base.data.remote.ApiResult;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Phong on 3/1/2017.
 */

public class LoginHelper {
    private final DataManager mDataManager;
    private final ApiResult mApiResult;
    private final CompositeDisposable mCompositeDisposable;

    public LoginHelper(CompositeDisposable compositeDisposable, ApiResult apiResult) {
        mCompositeDisposable = compositeDisposable;
        mApiResult = apiResult;
        mDataManager = ApplicationModules.getInstant().getDataManager();
    }

    public void login(String email, String password, String push_key) {
        Disposable disposable = mDataManager.login(email, password, push_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        if (user != null && mApiResult != null) {
                            mApiResult.onSuccess("");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mApiResult != null) {
                            mApiResult.onError(throwable.getMessage());
                        }
                    }
                });

        mCompositeDisposable.add(disposable);
    }
}
