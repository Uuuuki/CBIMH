package com.example.wyr.cbimh;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyr on 2018/3/22.
 */

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ImageButton mVideo;
    private ImageButton mPhoto;
    private ImageButton mLesson;
    private ImageButton mFriendMessage;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new PaddingDecoration(this));


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initializeData();

        RVAdapter adapter = new RVAdapter(inform);
        recyclerView.setAdapter(adapter);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        toolbar.setTitle("武汉理工幼儿园");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();


            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();


            }
            @Override
            public void onDrawerStateChanged(int newState){

            }
        };

        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        //设置菜单列表

        mVideo = findViewById(R.id.video);
        mPhoto = findViewById(R.id.photo);
        mLesson = findViewById(R.id.lesson);
        mFriendMessage = findViewById(R.id.friend_message);

        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Video.class);
                startActivity(intent);
            }
        });

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Photo.class);
                startActivity(intent);
            }
        });

        mLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Lesson.class);
                startActivity(intent);
            }
        });

        mFriendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,FriendMessage.class);
                startActivity(intent);
            }
        });





    }

    class Inform {
        String title;
        String content;

        Inform(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    private List<Inform> inform;

    private void initializeData() {
        inform = new ArrayList<>();
        inform.add(new Inform("园区公告", "由于天气影响，今明两天幼儿园的小朋友们进行两天的休假，" +
                "请大家各位爸爸妈妈在家多陪陪宝贝哟！\n" +
                "本园将在两周之后举办宝贝演讲比赛，请要参加的宝贝们做好充分的准备。"));
        inform.add(new Inform("今日课程", "今天是休假，暂时没有课程哟！"));
        inform.add(new Inform("老师点评", "今天宝贝在园区跑动，不小心摔伤擦破了皮，" +
                "请家长们好好照顾呀！"));
        inform.add(new Inform("今日作业", "学习10个英文单词。\n" +
                "完成20道数学题。\n" +
                "阅读5个小故事。\n" +
                "准备两周之后的演讲比赛"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}