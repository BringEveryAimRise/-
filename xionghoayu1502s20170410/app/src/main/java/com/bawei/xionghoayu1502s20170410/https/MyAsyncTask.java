package com.bawei.xionghoayu1502s20170410.https;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.bawei.xionghoayu1502s20170410.adapter.MyAdapter;
import com.bawei.xionghoayu1502s20170410.bean.Beans;
import com.google.gson.Gson;

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
 * Time by 2017/4/10 0010
 */

public class MyAsyncTask extends AsyncTask<String,String,String> {

    private HttpURLConnection conn;
    Context context;


    public MyAsyncTask(Context context){
        this.context=context;

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            if(conn.getResponseCode()==200){
                InputStream is = conn.getInputStream();
                BufferedReader buffered = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line=null;

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

        if(!TextUtils.isEmpty(s)){
            Gson gson = new Gson();
            Beans beans = gson.fromJson(s, Beans.class);




        }



    }
}
