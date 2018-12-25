package com.nios.downloadfilelib.utils;

import android.os.Environment;

import com.facebook.stetho.common.LogUtil;
import com.nios.downloadfilelib.http.Api;
import com.nios.downloadfilelib.http.RetrofitManager;

import java.io.File;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * @author Charay
 * @date 2018/7/2 15:06
 * @email charay@126.com
 * @desc 下载文件到sd卡中
 */
public class DownloadFileUtil {

    /**
     * 下载文件到sd卡
     * @param baseUrl baseurl地址 如：https://www.baidu.com/
     * @param fromUri 文件的资源路径，包含文件名称和扩展名  如：img/bau_logo.gif
     * @param toDir  要存储在sd卡中的目录
     * @param fileName  要存储的文件名称 包含扩展名
     */
    public static void downloadFileFromServer(String baseUrl,String fromUri, final String toDir,final String fileName) {
        RetrofitManager.reset(baseUrl);
        Api api = RetrofitManager.getInstance().getApi();
        api.downloadFile(fromUri)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<ResponseBody>>() {

                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        saveFile(response,toDir,fileName);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void saveFile(final Response<ResponseBody> response, String toDir,String fileName) {
        try {

            File toFileDir = new File(Environment.getExternalStorageDirectory().getPath() + "/" + toDir);
            if (!toFileDir.exists()) {
                toFileDir.mkdirs();
            }
            File file = new File(toFileDir, fileName);
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            // you can access body of response
            sink.writeAll(response.body().source());
            sink.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
