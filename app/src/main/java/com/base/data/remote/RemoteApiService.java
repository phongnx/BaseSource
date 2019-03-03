package com.base.data.remote;


import com.base.data.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Phong on 11/9/2016.
 */

public interface RemoteApiService {

    @FormUrlEncoded
    @POST("user/login")
    Observable<User> login(@Field("email") String email,
                           @Field("password") String password,
                           @Field("android_push_key") String android_push_key);

    @GET("user/list")
    Observable<List<User>> userList();

    @FormUrlEncoded
    @Multipart
    @POST("user/register")
    Observable<User> register(@Field("full_name") String full_name,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("android_push_key") String android_push_key,
                              @Part MultipartBody.Part file);


    class Creator {
        private static final String ENDPOINT = "https://api.pikit.co/api/";

        public static Retrofit newRetrofitInstance() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS);
            /**
             * need if Api is https.
             */
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//                        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1)
//                        .cipherSuites(
//                                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA)
//                        .build();
//                builder.connectionSpecs(Collections.singletonList(spec));
//            }

            OkHttpClient client = builder.build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    .create();

            return new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

        }

    }
}
