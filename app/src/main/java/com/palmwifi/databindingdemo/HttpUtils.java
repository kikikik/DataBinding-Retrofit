package com.palmwifi.databindingdemo;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Kiki on 2017/5/17.
 */

public class HttpUtils {

    private Context context;
    private OkHttpClient client;
    private Retrofit retrofit;
    private static HttpUtils instance;

    public interface GetDataInterface {
        @GET(Const.url)
        Observable<List<List<ItemBeanOrigin>>> getListData(@QueryMap Map<String, String> paramers);
    }

    public static HttpUtils getInstance(Context context) {
        synchronized (HttpUtils.class) {
            if (instance == null) {
                synchronized (HttpUtils.class) {
                    instance = new HttpUtils(context);
                }
            }
        }
        return instance;
    }

    private HttpUtils(Context context) {
        this.context = context;
        initOkHttp();
        initRetrofit();
    }

    private void initOkHttp() {
        File cacheFile = new File(context.getExternalCacheDir(), "httpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .build();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public void getData(int page, Subscriber<List<List<ItemBeanOrigin>>> subscription) {
        GetDataInterface service = retrofit.create(GetDataInterface.class);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("pageNum", page + "");
        parameters.put("pageSize", 15 + "");
        parameters.put("appkey", "80008");
        Observable<List<List<ItemBeanOrigin>>> listData = service.getListData(parameters);
        listData.subscribeOn(Schedulers.io())
                .subscribe(subscription);
    }
}
