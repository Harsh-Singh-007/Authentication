package com.harsh.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Button login1,register_1;
    String name,email1,mobile,password;
    EditText text_name,text_email,text_mobile,text_pass;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        login1 = (Button) findViewById(R.id.login_1);
        register_1 = (Button) findViewById(R.id.register_1);
        text_name = (EditText) findViewById(R.id.editText_1);
        text_mobile = (EditText) findViewById(R.id.editText_2);
        text_email = (EditText) findViewById(R.id.editText_3);
        text_pass = (EditText) findViewById(R.id.editText_4);
        DB = new DBHelper(this);

        //Buttons
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        });

        register_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= text_name.getText().toString().trim();
                email1 = text_email.getText().toString().trim();
                mobile = text_mobile.getText().toString().trim();
                password = text_pass.getText().toString().trim();

                if(name.isEmpty()|| email1.isEmpty() || mobile.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(Register.this,"Please enter all the credentials",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (checkpassword(password,name)) {
                        if(isValidEmailAddress(email1)){
                            Boolean checkemail = DB.checkemail(email1);
                            Boolean checknumber = DB.checknumber(mobile);
                            if(checkemail == false){
                                if(checknumber == false){
                                    Boolean insert = DB.insertData(name,email1,mobile,password);
                                    if(insert){
                                        Toast.makeText(Register.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Register.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Register.this,"Registered Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(Register.this,"Number already exists", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Register.this,"Email already exists", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }
        });
    }

    //Email verification
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //Password Verification
    private boolean checkpassword(String password, String name) {
        if(password.length() >= 8 || password.length() <= 15) {
            // boolean na = password.matches("(.*)" + name + "(.*)");
            //password.contains(name);
            //int na = password.indexOf(name);
            int counter = 0; //pointing s2
            int i = 0;
            for(;i<password.length();i++){
                if(counter==name.length())
                    break;
                if(name.charAt(counter)==password.charAt(i)){
                    counter++;
                }else{
                    //Special case where character preceding the i'th character is duplicate
                    if(counter>0){
                        i -= counter;
                    }
                    counter = 0;
                }
            }

            if(counter < name.length()) {
                if(Character.isLowerCase(password.charAt(0))) {
                    int up=0,num=0;//up=2,num=2
                    for(int j=0;j< password.length();j++)  {
                        if(password.charAt(j) >= '0' && password.charAt(j) <= '9') {
                            num++;
                        }
                        else if(Character.isUpperCase(password.charAt(j))) {
                            up++;
                        }
                    }
                    if(up>=2 && num >=2) {
                        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(password);
                        boolean res = m.find();
                        if(res){
                            return true;
                        }
                        else{
                            Toast.makeText(this,"Password should contain atleast one special character",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(this,"Password should contain atleast 2 numbers and 2 uppercase character",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,"Passwords first letter should be in lowercase character",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"Password should not contain name",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Password should contain atleast 8 character and less than 15 character ",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}