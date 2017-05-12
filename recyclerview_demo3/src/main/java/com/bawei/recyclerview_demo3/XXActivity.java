package com.bawei.recyclerview_demo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class XXActivity extends AppCompatActivity {

    private ArrayList<Beans> tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xx);

        Intent intent = getIntent();
        if(intent!=null){
            tag = (ArrayList<Beans>) intent.getSerializableExtra("tag");
        }

        if(tag!=null){
            TextView tv = (TextView) findViewById(R.id.xx_tv);
            tv.setText(tag.toString());
        }


    }
}
