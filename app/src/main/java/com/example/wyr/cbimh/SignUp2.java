package com.example.wyr.cbimh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyr on 2018/3/21.
 */

public class SignUp2 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_signup2);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //设置选项list
        List<String> sex_list = new ArrayList<String>();

        sex_list.add("我是孩子的爸爸");
        sex_list.add("我是孩子的妈妈");
        sex_list.add("您是孩子的爸爸还是妈妈");

        final List<String> school_list = new ArrayList<String>();

        school_list.add("武汉理工幼儿园");
        school_list.add("华中师范幼儿园");
        school_list.add("武汉大学附属幼儿园");
        school_list.add("华中农业幼儿园");
        school_list.add("光谷人民幼儿园");
        school_list.add("街道口职业技术幼儿园");
        school_list.add("湖北工业幼儿园");
        school_list.add("武汉纺织幼儿园");
        school_list.add("华中科技幼儿园");
        school_list.add("武汉地质幼儿园");
        school_list.add("东湖科技幼儿园");
        school_list.add("水果湖幼儿园");
        school_list.add("洪山科学幼儿园");
        school_list.add("青山幼儿园");
        school_list.add("余家头人民幼儿园");
        school_list.add("铁机路幼儿园");
        school_list.add("汉口商业幼儿园");
        school_list.add("汉阳机关幼儿园");
        school_list.add("请选择您的孩子的学校");


        //使用适配器适配列表
        MyArrayAdapter sexadapter = new MyArrayAdapter<String>(this,R.layout.spinner,sex_list);
        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sex = findViewById(R.id.sex);
        sex.setAdapter(sexadapter);

        MyArrayAdapter schooladapter = new MyArrayAdapter<String>(this,R.layout.spinner,school_list);
        schooladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner school = findViewById(R.id.school);
        school.setAdapter(schooladapter);


        //取消list最后一行显示，将其设置为hint
        sex.setSelection(sex_list.size() - 1,true);
        school.setSelection(school_list.size() - 1,true);


        //spinner选择事件
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sexselect = getResources().getStringArray(R.array.sex);
                Toast.makeText(SignUp2.this,sexselect[position],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] schoolselect = getResources().getStringArray(R.array.school);
                Toast.makeText(SignUp2.this,schoolselect[position],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button submit = findViewById(R.id.nextSignUp2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp2.this,Home.class);
                startActivity(intent);
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
