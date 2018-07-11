package cn.com.demo.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



public class RecyclerViewSingAdapter<T> extends RecyclerView.Adapter<FinalViewholder> {

    private final FinalAdapterListener mFinalAdapterListener;
    private ArrayList<ItemType> datas;
    private int mOneLyaout;
    private boolean refreshEnable = false;
    private boolean loadMoreEnable = false;

    public RecyclerViewSingAdapter(ArrayList<ItemType> datas, int oneLayout, FinalAdapterListener finalAdapterListener, boolean refreshEnable, boolean loadMoreEnable) {
        this.datas = datas;
        mOneLyaout = oneLayout;
        this.mFinalAdapterListener = finalAdapterListener;
        this.refreshEnable = refreshEnable;
        this.loadMoreEnable = loadMoreEnable;
    }

    @Override
    public FinalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ONETYPE:
                try {
                    view = LayoutInflater.from(parent.getContext()).inflate(mOneLyaout, parent, false);

                } catch (Exception e) {

                }
                break;
            case REFRESHTYPE:
                try {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rehresh_layout, parent, false);
                } catch (Exception e) {
                }
                break;

            case LOADMORETYPE:
                try {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_layout, parent, false);

                } catch (Exception e) {


                }
                break;

            default:
                break;

        }
        return new FinalViewholder(view);
    }


    @Override
    public void onBindViewHolder(FinalViewholder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case ONETYPE:
                if (mFinalAdapterListener != null) {
                    mFinalAdapterListener.oneBindView(holder, position);
                }
                break;
            default:
                break;
        }

    }


    public interface FinalAdapterListener {
        void oneBindView(FinalViewholder hodler, int position);
    }


    @Override
    public int getItemCount() {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i) instanceof RefreshType) {
                datas.remove(i);
            }
            if (datas.size() > 0) {
                if (datas.get(i) instanceof LoadMoreType) {
                    datas.remove(i);
                }
            }
        }
        if (refreshEnable) {
            this.datas.add(0, new RefreshBean());

        }
        if (loadMoreEnable) {
            //添加加载更多布局
            this.datas.add(new LoadMoreBean());

        }


        return datas.size();
    }


    public static final int REFRESHTYPE = 101;
    public static final int LOADMORETYPE = 102;
    public static final int ONETYPE = 103;

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i) instanceof RefreshType) {
                datas.remove(i);
            }
            if (datas.size() > 0) {
                if (datas.get(i) instanceof LoadMoreType) {
                    datas.remove(i);
                }

            }
        }
        if (refreshEnable) {
            this.datas.add(0, new RefreshBean());

        }
        if (loadMoreEnable) {
            //没有加载更多布局加载一个
            this.datas.add(new LoadMoreBean());
        }


        if (datas.get(position) instanceof RefreshType) {
            return REFRESHTYPE;
        }


        if (datas.get(position) instanceof OneType) {
            return ONETYPE;
        }

        if (datas.get(position) instanceof LoadMoreType) {
            return LOADMORETYPE;
        }

        return LOADMORETYPE;
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

}