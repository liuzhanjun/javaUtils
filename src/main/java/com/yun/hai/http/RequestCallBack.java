package com.yun.hai.http;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 刘展俊 on 2017/4/22.
 */

public abstract class RequestCallBack <R> {
    public abstract void before();
    public abstract void success(R result);
    public abstract void failure(HttpUtils.NetThrowable error);
    public abstract void finish();
    public void progress(long currentByte,long countByte){

    }
    private Type mType;

    public Type getmType() {
        return mType;
    }

    public RequestCallBack() {
        mType = getSuperclassTypeParameter(getClass());
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter. 请在RequestCallBack上添加泛型类型");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types
                .canonicalize(parameterized.getActualTypeArguments()[0]);
    }


}
