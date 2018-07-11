package com.study.wl.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mR1;
    private RecyclerView mR2;
    private RecyclerView mR3;
    private ArrayList<TextBean> mList1;
    private ArrayList<PicBean> mList2;
    private ArrayList<PicBean> mList3;


    private String[] title1 = {"功能1","功能2","功能3"};

    private int[] icon1 = {R.mipmap.i1_1,R.mipmap.i1_2,R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
    private int[] icon2 = {R.mipmap.i2_1,R.mipmap.i2_2,R.mipmap.ic_launcher};
    private int[] icon3 = {R.mipmap.i3_1,R.mipmap.i3_2,R.mipmap.ic_launcher};

    private TextAdapater textAdapater1;
    private RecyclerViewSingAdapter mAdapter2;
    private RecyclerViewSingAdapter mAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);


        initview();
        init();
    }

    private void initview() {
        mR1 = (RecyclerView) findViewById(R.id.recyclerview1);
        mR2 = (RecyclerView) findViewById(R.id.recyclerview2);
        mR3 = (RecyclerView) findViewById(R.id.recyclerview3);

        mList1 = new ArrayList<>();
        for (int i = 0; i < title1.length; i++) {
            TextBean textBean = new TextBean(title1[i]);
            mList1.add(textBean);
        }

        mList2 = new ArrayList<>();

        for (int i = 0; i < icon1.length; i++) {
            PicBean picBean = new PicBean();
            picBean.setPic(icon1[i]);
            mList2.add(picBean);

        }
        mList3 = new ArrayList<>();



    }

    private void init() {

        mR1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mR2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mR3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        textAdapater1 = new TextAdapater(mList1);
        mR1.setAdapter(textAdapater1);

        textAdapater1.setOnClickListener(new TextAdapater.OnItemClickListener() {
            @Override
            public void onRecyclerItemClick(View view, int position) {
                switch (position) {
                    case 0:

                        mList2.clear();
                        for (int i = 0; i < icon1.length; i++) {
                            PicBean picBean = new PicBean();
                            picBean.setPic(icon1[i]);
                            mList2.add(picBean);

                        }
                        mAdapter2.notifyDataSetChanged();

                        break;

                    case 1:
                        mList2.clear();
                        for (int i = 0; i < icon2.length; i++) {
                            PicBean picBean = new PicBean();
                            picBean.setPic(icon2[i]);
                            mList2.add(picBean);

                        }
                        mAdapter2.notifyDataSetChanged();


                        break;

                    case 2:
                        mList2.clear();
                        for (int i = 0; i < icon3.length; i++) {
                            PicBean picBean = new PicBean();
                            picBean.setPic(icon3[i]);
                            mList2.add(picBean);

                        }
                        mAdapter2.notifyDataSetChanged();
                        break;
                }

            }
        });


        mAdapter2 = new RecyclerViewSingAdapter(mList2);
        mR2.setAdapter(mAdapter2);

        mAdapter2.setOnItemClickListener(new RecyclerViewSingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                mList3.add(mList2.get(position));
                mAdapter3.notifyDataSetChanged();
            }
        });

        mAdapter3 = new RecyclerViewSingAdapter(mList3);
        mR3.setAdapter(mAdapter3);
    }



}
