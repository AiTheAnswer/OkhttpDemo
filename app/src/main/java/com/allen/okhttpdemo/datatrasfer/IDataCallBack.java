package com.allen.okhttpdemo.datatrasfer;

/**
 * Created by Allen on 2018/1/30.
 */

public interface IDataCallBack<T extends WinnerResponse> {
    void onSuccess(T result);

    void onError(int errorCode, String errorMessage);
}
