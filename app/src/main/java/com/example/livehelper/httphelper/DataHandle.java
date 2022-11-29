package com.zrkaxt.testmachine.httphelper;

import org.json.JSONException;

public  interface DataHandle<T> {
    public abstract void loadSuccess(T data);
    public abstract void loadFailure(HttpReturnData failRet);
}
