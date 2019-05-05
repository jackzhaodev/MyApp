package com.abc.myapp.network;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.abc.myapp.BuildConfig;
import com.abc.myapp.config.MyApplication;
import com.abc.myapp.entity.CommonResponse;
import com.abc.myapp.utils.ConstantUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class ApiClient {
    /**
     * 线上服务器
     */
    private static final String BASE_URL = "http://store.haopingruchao.com/";
    //    /**
//     * 本地服务器
//     */
//    private static final String BASE_URL = "http://192.168.8.129:8033/";
    private static final String AUTHORIZATION = "Authorization";
    private static final int DEFAULT_SECONDS = 30;
    private static ApiService sApiService;

    public static ApiService getApiService() {

        if (sApiService == null) {
            // 设置OkHttpClient
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            // 设置连接超时
            clientBuilder.connectTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS);
            // 设置写入超时
            clientBuilder.writeTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS);
            // 设置读取超时
            clientBuilder.readTimeout(DEFAULT_SECONDS, TimeUnit.SECONDS);
            // 添加网络请求日志打印拦截器
            // debug模式希望可以看到log, release模式下就不需要这些log
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(loggingInterceptor);
            }
            // 设置统一的header拦截器
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request finalRequest = originalRequest.newBuilder()
                            .header(AUTHORIZATION, MyApplication.getInstance().getToken())
                            .method(originalRequest.method(), originalRequest.body())
                            .build();
                    return chain.proceed(finalRequest);
                }
            };
            clientBuilder.addInterceptor(headerInterceptor);

            // 设置统一的异常处理拦截器
            Interceptor exceptionInterceptor = new Interceptor() {
                @NonNull
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody responseBody = response.body();
                    BufferedSource source;
                    if (responseBody != null) {
                        source = responseBody.source();
                        // Buffer the entire body
                        source.request(Long.MAX_VALUE);
                        Buffer buffer = source.buffer();
                        MediaType contentType = responseBody.contentType();
                        if (contentType != null) {
                            Charset utf8 = contentType.charset(Charset.forName("UTF8"));
                            if (utf8 != null) {
                                String bodyString = buffer.clone().readString(utf8);
                                Gson gson = new Gson();
                                CommonResponse commonResponse = gson.fromJson(bodyString, CommonResponse.class);
                                int code = commonResponse.getCode();
                                // 统一处理700 token过期
                                if (code == ConstantUtils.TOKEN_EXPIRED) {
                                    // 发出通知
                                    LocalBroadcastManager manager = LocalBroadcastManager.getInstance(MyApplication.getAppContext());
                                    Intent intent = new Intent(ConstantUtils.OFFLINE_RECEIVER);
                                    intent.putExtra("code", ConstantUtils.TOKEN_EXPIRED);
                                    manager.sendBroadcast(intent);
                                }
                                // 统一处理701 其他设备登录
                                if (code == ConstantUtils.OTHER_DEVICE_LOGIN) {
                                    // 发出通知
                                    LocalBroadcastManager manager = LocalBroadcastManager.getInstance(MyApplication.getAppContext());
                                    Intent intent = new Intent(ConstantUtils.OFFLINE_RECEIVER);
                                    intent.putExtra("code", ConstantUtils.OTHER_DEVICE_LOGIN);
                                    manager.sendBroadcast(intent);
                                }
                            }
                        }
                    }
                    return response;
                }
            };
            clientBuilder.addInterceptor(exceptionInterceptor);

            OkHttpClient client = clientBuilder.build();
            // 设置Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            // 创建ApiService
            sApiService = retrofit.create(ApiService.class);
        }
        return sApiService;
    }
}
