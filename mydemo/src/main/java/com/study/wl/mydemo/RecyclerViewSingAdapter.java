package com.study.wl.mydemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by ${WU} on 2018/7/11.
 */
class RecyclerViewSingAdapter extends RecyclerView.Adapter<RecyclerViewSingAdapter.ImageViewHolder>{
    public RecyclerViewSingAdapter(ArrayList<PicBean> mdata) {
        this.mdata = mdata;
    }

    private ArrayList<PicBean> mdata;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);

        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        holder.image2.setImageResource(mdata.get(position).getPic());

        if (onItemClickListener != null) {
            holder.imagelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.imagelayout, layoutPosition);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout imagelayout;
        private final ImageView image2;

        public ImageViewHolder(View itemView) {
            super(itemView);


            imagelayout = (LinearLayout) itemView.findViewById(R.id.imagelayout);
            image2 = (ImageView) itemView.findViewById(R.id.image2);

        }
    }
}
