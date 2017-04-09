package com.bawei.bear201704_study_dome.https;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/4/7 0007
 */

public class MyAsyncTask  extends AsyncTask<String,Integer,String> {

    Handler handler;

    public MyAsyncTask(Handler handler){
        this.handler=handler;
    }


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            if(conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                BufferedReader buffered = new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuilder sb = new StringBuilder();

                while((line=buffered.readLine())!=null){
                    sb.append(line);
                }

                buffered.close();
                is.close();

                return sb.toString();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (!TextUtils.isEmpty(s)){
            Message msg = new Message();
            msg.what=0;
            msg.obj=s;
            handler.sendMessage(msg);
        }

    }
}
