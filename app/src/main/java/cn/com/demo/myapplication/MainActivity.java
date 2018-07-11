package cn.com.demo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mR1;
    private RecyclerView mR2;
    private RecyclerView mR3;
    private ArrayList<TitleBean> mList1;
    private ArrayList<PicBean> mList2;
    private ArrayList<PicBean> mList3;
    private RecyclerViewSingAdapter mAdapter1;

    private String[] title = {"标题1","标题2","标题3","标题4","标题5","标题6","标题7","标题8"};
    private int[] icon1 = {R.mipmap.i1_1,R.mipmap.i1_2};
    private int[] icon2 = {R.mipmap.i2_1,R.mipmap.i2_2};
    private int[] icon3 = {R.mipmap.i3_1,R.mipmap.i3_2};
    private RecyclerViewSingAdapter mAdapter2;
    private RecyclerViewSingAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNew();
        init();
    }

    private void initNew() {
        mR1 = findViewById(R.id.recycler_view1);
        mR2 = findViewById(R.id.recycler_view2);
        mR3 = findViewById(R.id.recycler_view3);

        mList1 = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            TitleBean titleBean = new TitleBean();
            titleBean.setTitle(title[i]);
            mList1.add(titleBean);
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
        mR1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mR2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mR3.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));

        mAdapter1 = new RecyclerViewSingAdapter(mList1, R.layout.item_text, new RecyclerViewSingAdapter.FinalAdapterListener() {
            @Override
            public void oneBindView(FinalViewholder hodler, final int position) {
                RelativeLayout rlRoot1 = (RelativeLayout) hodler.getViewByid(R.id.rl_root1);
                TextView tvTitle = (TextView) hodler.getViewByid(
                        R.id.tv_title);
                tvTitle.setText(mList1.get(position).getTitle());
                rlRoot1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

//                                标题 有几个写几个
                        }
                    }
                });
            }
        },false,false);
        mR1.setAdapter(mAdapter1);

        mAdapter2 = new RecyclerViewSingAdapter(mList2, R.layout.item_pic, new RecyclerViewSingAdapter.FinalAdapterListener() {
            @Override
            public void oneBindView(FinalViewholder hodler, final int position) {
                ImageView pic = (ImageView) hodler.getViewByid(R.id.iv_pic);
                pic.setImageResource(mList2.get(position).getPic());
                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mList3.add(mList2.get(position));
                        mAdapter3.notifyDataSetChanged();
                    }
                });
            }
        },false,false);
        mR2.setAdapter(mAdapter2);

        mAdapter3 = new RecyclerViewSingAdapter(mList3, R.layout.item_pic, new RecyclerViewSingAdapter.FinalAdapterListener() {
            @Override
            public void oneBindView(FinalViewholder hodler, int position) {
                ImageView pic = (ImageView) hodler.getViewByid(R.id.iv_pic);
                pic.setImageResource(mList3.get(position).getPic());
            }
        },false,false);
        mR3.setAdapter(mAdapter3);
    }

}
