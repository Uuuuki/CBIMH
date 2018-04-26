package com.example.wyr.cbimh;

import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyr on 2018/3/27.
 */

public class Photo extends AppCompatActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager llm;
    private RecyclerView recyclerView;
    private PhotoRVAdapter adapter;

    boolean isLoading;
    private Handler handler = new Handler();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        toolbar = (Toolbar) findViewById(R.id.toolbar_photo);

        toolbar.setTitle("宝贝动态");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swipe);


        recyclerView = findViewById(R.id.photo_recyclerView);
        recyclerView.setHasFixedSize(true);

        llm = new LinearLayoutManager(Photo.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new PaddingDecoration(this));


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        init();

        adapter = new PhotoRVAdapter(inform);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Photo.this,"刷新成功",Toast.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);

            }

        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                inform.add(new Inform("abc","abc"));
//                inform.add(new Inform("abc","abc"));
//                adapter = new PhotoRVAdapter(inform);
//                recyclerView.setAdapter(adapter);
//                Log.d("test111", "StateChanged = " + newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//
//                Log.d("test112", "onScrolled");
//
//
//                int lastVisibleItemPosition = llm.findLastVisibleItemPosition();
//                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
//                    Log.d("test", "loading executed");
//
//                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
//                    if (isRefreshing) {
//                        adapter.notifyItemRemoved(adapter.getItemCount());
//                        return;
//                    }
//                    if (!isLoading) {
//                        isLoading = true;
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                inform.add(new Inform("abc","abc"));
//                                adapter.notifyDataSetChanged();
//                                swipeRefreshLayout.setRefreshing(false);
//                                adapter.notifyItemRemoved(adapter.getItemCount());
//                                Log.d("test", "load more completed");
//                                isLoading = false;
//                            }
//                        }, 1000);
//                    }
//                }
            }
        });



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

    class Inform {
        String name;
        String message;

        Inform(String title, String content) {
            this.name = title;
            this.message = content;
        }
    }

    private List<Inform> inform;

    private void init(){
        inform = new ArrayList<>();
        inform.add(new Inform("刘妈妈","我家宝贝今天考试第一名啦！！！"));
        inform.add(new Inform("刘妈妈","我家宝贝今天考试第一名啦！！！"));


    }
}
