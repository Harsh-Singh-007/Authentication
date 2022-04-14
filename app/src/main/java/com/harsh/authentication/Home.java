package com.harsh.authentication;

import static com.harsh.authentication.DBmain.TABLENAME;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter adapter;

    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,post.class);
                startActivity(intent);
            }
        });

        dBmain = new DBmain(this);
        findId();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +TABLENAME+"",null);
        ArrayList<Model> models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[] avatar = cursor.getBlob(1);
            String title = cursor.getString(2);
            String desc = cursor.getString(3);
            models.add(new Model(id,avatar,title,desc));
        }
        cursor.close();
        adapter = new MyAdapter(this,R.layout.singledata,models,sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.recview);
    }
}