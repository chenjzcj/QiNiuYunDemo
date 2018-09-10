package com.suk.qiniuyundemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    /**
     * 签名
     */
    private static String TOKEN = "GvEA59t5wNoEq85_S-kY8aYWk_5p1dhCUh7sNwoN:bAtreLaHTRAxCqSpGKIiOf49Dow=:eyJzY29wZSI6InJhdGVzdCIsImRlYWRsaW5lIjoxNTAwMzUwNzM2fQ==";
    /**
     * 上传地址
     */
    private static String HOST = "http://ot9mcsdri.bkt.clouddn.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 上传数据到七牛云服务器
     *
     * @param view View
     */
    public void upload(View view) {
        UploadManager uploadManager = new UploadManager();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        byte[] data = Bitmap2Bytes(bitmap);
        String fileName = "hello.aapng";

        //data = "hello".getBytes();
        /*fileName = "hello.txt";*/

        uploadManager.put(data, fileName, TOKEN, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                Log.i("qiniu 访问链接 = ", HOST + key);
                Log.i("qiniu info = ", info.toString());
                Log.i("qiniu response = ", response.toString());
            }
        }, null);
    }

    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
