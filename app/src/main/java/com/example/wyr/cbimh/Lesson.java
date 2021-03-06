package com.example.wyr.cbimh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyr on 2018/3/27.
 */

public class Lesson extends AppCompatActivity {

    private Toolbar toolbar;
    private CourseTableView courseTableView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson);

        toolbar = (Toolbar) findViewById(R.id.toolbar_lesson);

        toolbar.setTitle("宝贝课表");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        courseTableView = (CourseTableView) findViewById(R.id.ctv);
        List<Course> list = new ArrayList<>();
        Course c1 = new Course();
        c1.setDay(1);
        c1.setDes("数学");
        c1.setJieci(1);
        c1.setClassRoomName("小2班");
        list.add(c1);

        Course c2 = new Course();
        c2.setDay(2);
        c2.setDes("语文");
        c2.setJieci(6);
        list.add(c2);

        courseTableView.updateCourseViews(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
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