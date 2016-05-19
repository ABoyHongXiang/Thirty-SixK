package com.hongxiang.kforthirtysix;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.util.GsonRequest;

import javax.xml.transform.ErrorListener;

/**
 * Created by dllo on 16/5/16.
 */
public class VolleySingle {
    private static Context mContext;
    private RequestQueue queue;//请求队列
    private static VolleySingle ourInstance = new VolleySingle(mContext);

    //获取单例对象
    public static VolleySingle getInstance() {
        return ourInstance;
    }

    private VolleySingle(Context context) {
        mContext = context.getApplicationContext();
        getQueue();

    }


    //新建一个请求队列
    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(mContext);
        }
        return queue;
    }

    public static final String TAG = "VolleySingleton";

    //添加请求
    public <T> void addRequest(Request<T> request) {
        request.setTag(TAG);//添加书签,方便管理
        queue.add(request);//将请求队列添加到队列当中
    }

    public <T> void addRequest(Request<T> request, Object tag) {
        request.setTag(tag);//添加书签,方便管理
        queue.add(request);//将请求队列添加到队列当中
    }

    public void addRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        addRequest(stringRequest);

    }

    public <T> void addRequest(String url, Response.ErrorListener errorListener, Response.Listener<T> listener, Class<T> mClass) {
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, errorListener, listener, mClass);
        addRequest(gsonRequest);

    }

    public void removeRequest(Object tag) {
        queue.cancelAll(tag);//根据不同的tag移除队列;
    }

    public static void addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        getInstance().addRequest(url, listener, errorListener);
    }

    public static <T> void addRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> mClass) {
        getInstance().addRequest(url, listener, errorListener, mClass);
    }
}
