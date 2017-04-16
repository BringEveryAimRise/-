package com.bawei.on_demo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

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
 * Time by 2017/4/15 0015
 */

public class MyAsyncTask extends AsyncTask<String,Integer,String> {

    int tag;
    Handler handler;

    public MyAsyncTask(int tag,Handler handler){
        this.tag=tag;
        this.handler=handler;
    }


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            if(conn.getResponseCode()==200){
                InputStream inputStream = conn.getInputStream();
                BufferedReader buffered = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String line;

                while ((line=buffered.readLine())!=null){
                    sb.append(line);
                }

                buffered.close();
                inputStream.close();

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

        Message message = new Message();
        message.obj=s;
        message.what=tag;
        handler.sendMessage(message);
    }
}
