package com.harsh.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login,register;
    String name,pass;
    EditText txt_name,txt_pass;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        txt_name = findViewById(R.id.editText1);
        txt_pass = findViewById(R.id.editText2);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txt_name.getText().toString();
                pass = txt_pass.getText().toString();

                if(name.equals(" ")|| pass.equals(" ")){
                    Toast.makeText(MainActivity.this,"Please enter all the details",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean result = name.matches("[0-9]+");
                    if(result){
                        Boolean checknumberpassword = DB.checknumberpassword(name,pass);
                        if(checknumberpassword){
                            Intent intent = new Intent(MainActivity.this,post.class);
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please enter correct number and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Boolean checkemailpassword = DB.checkemailpassword(name,pass);
                        if(checkemailpassword){
                            Intent intent = new Intent(MainActivity.this,post.class);
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please enter correct email and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}