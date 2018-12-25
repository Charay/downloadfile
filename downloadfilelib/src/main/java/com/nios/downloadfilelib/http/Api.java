package com.nios.downloadfilelib.http;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * api 接口
 *
 * @author Charay
 * @version 2.0
 * @date 2018/12/25 15:18
 */

public interface Api {


    @GET("/{fromUri}")
    @Streaming
    Observable<Response<ResponseBody>> downloadFile(@Path(value = "fromUri", encoded = true) String fromUri);
}
