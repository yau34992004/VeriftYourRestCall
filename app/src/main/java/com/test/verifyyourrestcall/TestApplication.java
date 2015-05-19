package com.test.verifyyourrestcall;

import android.app.Application;
import android.util.Log;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rex.yau on 5/14/2015.
 */
public class TestApplication extends Application {

    private static final String TAG = "TestApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        AsyncHttpServer server = new AsyncHttpServer();
        server.get(ServerConfig.PATH_BINARY, new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                try {

                    Log.d(TAG, "checkCallingTestImage");


                    //send by stream
                    InputStream inputStream = getAssets().open("image.jpg");
                    Log.d(TAG, "checkFileLength->" + inputStream.available());
                    response.sendStream(inputStream, inputStream.available());

                    //send by file
//                    response.sendFile(new File("yourFilePath"));
                } catch (IOException e) {
                    Log.d(TAG, "IOException");
                    response.sendFile(null);
                } catch (Exception e) {
                    Log.d(TAG, "unExpectedException");
                    response.sendFile(null);
                }
//                response.sendFile(null);
            }
        });

        server.get(ServerConfig.PATH_JSON, new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {

                Log.d(TAG, "checkCallingTestJson");

                response.send("send you json string or use json Object");
            }
        });
        server.listen(ServerConfig.PORT);
    }
}
