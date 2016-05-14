package com.hongxiang.kforthirtysix.util;

import com.android.volley.NetworkResponse;
        import com.android.volley.ParseError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.toolbox.HttpHeaderParser;
        import com.google.gson.Gson;

        import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/5/13.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;//以后的实体类
    //构造方法中我们传入的参数第一个 请求数据类型,第二个参数,URL
    //第三个参数失败时的回调,第四个成功时候的回调,第五个就是实体类


    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> mListener, Class<T> mClass) {
        super(method, url, listener);
        this.mListener = mListener;
        this.mGson = new Gson();
        this.mClass = mClass;
    }

    //返回我们想要解析后的数据,在这里首先将response转化成String类型的数据,然后通过Gson进行解析,
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //成功之后的回调 第一个参数我们将data进行解析,第二个参数就是我们的缓存入口,这里的缓存是规定好的
            //我们直接将response请求作为缓存入口
            return Response.success(mGson.fromJson(data, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    //对请求的一个反馈,反馈就是我的上面定义好的接口对象
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
