package com.harsh.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Views extends AppCompatActivity {

    TextView title,desc;
    ImageView image;
    Button viewsback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        viewsback = (Button) findViewById(R.id.viewsback);
        image = (ImageView) findViewById(R.id.viewsimage);
        title = (TextView) findViewById(R.id.title);
        desc = (TextView) findViewById(R.id.viewsdescription);

        image.setImageResource(getIntent().getIntExtra("image",0));
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("description"));
    }
}