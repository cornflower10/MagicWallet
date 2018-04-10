package com.qlzgzg.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class BaseHttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("token", "f98aef708af140948e6962ab0a215333")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);

    }


}
