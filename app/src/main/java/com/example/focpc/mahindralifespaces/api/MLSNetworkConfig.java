package com.example.focpc.mahindralifespaces.api;

import android.text.TextUtils;

import com.example.focpc.mahindralifespaces.utils.MlsApp;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramees T on 04-12-2017.
 */

public class MLSNetworkConfig {

    private String endPoint;
    private boolean isLogsEnabled;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String APPLICATION_TOKEN = "sec_key";


    public MLSNetworkConfig(String endPoint, boolean isLogsEnabled) {
        this.endPoint = endPoint;
        this.isLogsEnabled = isLogsEnabled;
    }

    public MLSApiInterface createRetrofitAdapter() {
        Retrofit.Builder mConverterFactory = new Retrofit.Builder()
                .baseUrl(endPoint).addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (TextUtils.isEmpty(MlsUtils.getApiToken(MlsApp.getContext()))) {
                    return chain.proceed(originalRequest);
                }

                Request authorisedRequest = originalRequest.newBuilder()
                        .header(ACCEPT, "application/json")
                        .header(CONTENT_TYPE, "application/json")
                        .header(APPLICATION_TOKEN, MlsUtils.getApiToken(MlsApp.getContext()))
                        .build();
                return chain.proceed(authorisedRequest);
            }
        });

        if (isLogsEnabled) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        return  mConverterFactory.client(httpClient.build()).build().create(MLSApiInterface.class);
    }
}
