package com.test.verifyyourrestcall;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "checkJsonUrl->" + ServerConfig.getJSONUrl());
        AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(ServerConfig.getJSONUrl()), new AsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, String result) {

                Log.d(TAG, "checkCompleteRequest");
                if (e != null) {
                    Log.e(TAG, e.getMessage());
                }

                if (result == null) {
                    Log.d(TAG, "checkEmptyResponse->");
                } else {
                    Log.d(TAG, "checkResult->" + result);
                }

            }
        });

        Log.d(TAG, "checkBinaryUrl->" + ServerConfig.getBinaryUrl());

        File targetFile = new File(Environment.getExternalStorageDirectory(), "testImage.jpg");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        String targetPath = targetFile.getPath();
        Log.d(TAG, "checkTargetPath->" + targetPath);
        AsyncHttpClient.getDefaultInstance().executeFile(new AsyncHttpGet(ServerConfig.getBinaryUrl()), targetPath, new AsyncHttpClient.FileCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, File result) {

                Log.d(TAG, "checkCompleteFileRequest");

                if (result != null) {
                    Log.d(TAG, "checkFileResponse->" + result.getName());
                } else {
                    Log.d(TAG, "checkFileEmpty");
                }
            }
        });
    }

}
