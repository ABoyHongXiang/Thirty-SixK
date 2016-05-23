package com.hongxiang.kforthirtysix.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/5/16.
 */
public class VolleySingle {
    private static Context mContext;
    private RequestQueue requestQueue;//请求队列
    private static VolleySingle ourInstance;

    public static VolleySingle getInstance() {
        if (ourInstance == null) {
            ourInstance = new VolleySingle();
        }
        return ourInstance;
    }

    public VolleySingle() {
        //获取Application里面的context
        mContext = MyApplication.getContext();
        requestQueue = getRequestQueue();//初始化我的请求队列
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        return requestQueue;
    }

    public static final String TAG = "VolleySingleton";

    //添加请求
    public <T> void _addRequest(Request<T> request) {
        request.setTag(TAG);//为我的请求添加标签,方便管理
        requestQueue.add(request);//将请求添加到队列当中
    }

    public <T> void _addRequest(Request<T> request, Object tag) {
        request.setTag(tag);//为我的请求添加标签,方便管理
        requestQueue.add(request);//将请求添加到队列当中
    }

    //创建StringRequest
    //三个参数分别是url接口网址
    //成功时候的回调,失败时候的回调
    public void _addRequest(String url,
                            //这里为我们成功时候的你回调加上String类型的泛型
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);
        //将请求加入到队列
        _addRequest(stringRequest);
    }

    public <T> void _addRequest(String url, Class<T> tClass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        GsonRequest gsonRequest = new GsonRequest(Request.Method.GET, url, errorListener, listener, tClass);
        _addRequest (gsonRequest);
    }

    //这个方法是将请求移除队列
    public void removeRequest(Object tag) {
        requestQueue.cancelAll(tag);//根据不同的tag移除出队列
    }

    public static void addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        //获取单例的对象.调用添加请求的方法(这个是StringRequest的请求)
        getInstance()._addRequest(url, listener, errorListener);
    }

    public static <T> void addRequest(String url, Class<T> mclass, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        //同上方法将GsonRequest强求加入但里的队列中
        getInstance()._addRequest(url, mclass, listener, errorListener);
    }

}