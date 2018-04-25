package com.qlzgzg.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class BaseHttpHeaderInterceptor implements Interceptor {
    private Context context;
    public BaseHttpHeaderInterceptor(Context context){
        this.context = context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .addHeader("token",
                        context.getSharedPreferences("token",Context.MODE_PRIVATE)
                                .getString("token","") )
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
//"f98aef708af140948e6962ab0a215333"
    }


}
