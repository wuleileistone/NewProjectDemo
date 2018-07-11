package com.study.wl.mydemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ${WU} on 2018/7/11.
 */
public class TextAdapater extends RecyclerView.Adapter<TextAdapater.TextViewHolder>{

    private ArrayList<TextBean> mData;

    public TextAdapater(ArrayList<TextBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_recycler_view, parent, false);
        TextViewHolder textViewHolder = new TextViewHolder(view);

        return textViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TextViewHolder holder, final int position) {

        holder.title.setText(mData.get(position).getText());

        if (onItemClickListener != null) {
            holder.textlinearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClickListener.onRecyclerItemClick(holder.textlinearlayout, layoutPosition);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onRecyclerItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    class TextViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final LinearLayout textlinearlayout;

        public TextViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            textlinearlayout = (LinearLayout) itemView.findViewById(R.id.textlinearlayout);
        }
    }
}
