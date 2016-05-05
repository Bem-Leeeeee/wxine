package com.wxine.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wxine.android.model.Info;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> implements View.OnClickListener {
    List<Integer> image; //图片
    List<String> usename; //名字
    List<String> time;    //文本
    private PopupWindow popupwindow;

    public TimelineAdapter(List<Integer> image, List<String> usename, List<String> time) {
        this.image = image;
        this.usename = usename;
        this.time = time;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item,
                viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.icon.setImageResource(image.get(i));
        myViewHolder.name.setText(usename.get(i));
        myViewHolder.content.setText(time.get(i));
    }

    @Override
    public int getItemCount() {
        return usename == null ? 0 : usename.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Info) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView content;
        CardView cardView;
        ImageView imitem;
        Button btn1;
        Button btn2;
        Button btn3;
        Button btn4;
        public MyViewHolder(final View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.userimage);
            name = (TextView) itemView.findViewById(R.id.username);
            content = (TextView) itemView.findViewById(R.id.listview_time);



            cardView = (CardView) itemView.findViewById(R.id.relative);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(content.getContext(), Event_content.class);
                    content.getContext().startActivity(intent);

                }
            });
            btn1 = (Button) itemView.findViewById(R.id.btn1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onKQClick(v, (Info) v.getTag(), getLayoutPosition());
                }
            });

            btn2 = (Button) itemView.findViewById(R.id.btn2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onQJClick(itemView, (Info) v.getTag(), getLayoutPosition());
                }
            });

            btn3 = (Button) itemView.findViewById(R.id.btn3);
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onPJClick(itemView, (Info) v.getTag(), getLayoutPosition());
                }
            });

            btn4 = (Button) itemView.findViewById(R.id.btn4);
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onQDClick(itemView, (Info) v.getTag(), getLayoutPosition());
                }
            });
        }
    }


    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;


    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Info data);

        void onKQClick(View view, Info da, int position);

        void onQJClick(View view, Info da, int position);

        void onPJClick(View view, Info da, int position);

        void onQDClick(View view, Info da, int position);
    }
}
