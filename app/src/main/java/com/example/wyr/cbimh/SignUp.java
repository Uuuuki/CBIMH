package com.example.wyr.cbimh;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wyr on 2018/3/20.
 */

public class SignUp extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_signup);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //创建数据库
        dbHelper = new MyDatabaseHelper(this,"UserTable.db",null,1);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ContentValues values = new ContentValues();


        //下一步按钮动作
        Button next = findViewById(R.id.nextSignUp);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,SignUp2.class);


                TextView phonenum = findViewById(R.id.inputPhoneNumber);
                String phonenumber = phonenum.getText().toString();

                TextView password = findViewById(R.id.inputPassword);
                String pw = password.getText().toString();

                TextView passwordagain = findViewById(R.id.inputPasswordAgain);
                String pwa = passwordagain.getText().toString();


                if(pw.equals(pwa)){

                    //将注册信息插入数据库操作

                    //values.put("phoneNumber",phonenumber);
                    //values.put("password",pw);
                    //db.insert("UserTable",null,values);

                    startActivity(intent);

                }else{
                    Toast.makeText(SignUp.this,"密码不一致",Toast.LENGTH_SHORT).show();


                }


            }
        });


        toolbar.setTitle("注册");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
