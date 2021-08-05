package com.example.FinalProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static java.lang.Math.min;
import static com.example.FinalProject.challengeItemAdapter.mytotalPoint;


public class fundingItemAdapter extends RecyclerView.Adapter<fundingItemAdapter.ViewHolder> {

    private ArrayList<fundingItem> mData = null ;
    private OnItemClickForFunding mCallback;
    static int left_pointt;
    int tar_pointt;
    static int pointt;
    static int fund_pointt;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView funding;
        TextView inst;
        TextView inst_desc;
        ImageView img;
        SeekBar seekBar;
        ProgressBar progressbar;

        TextView seekmin;
        TextView seek_max;
        TextView tar_point;
        TextView acu_point;
        TextView left_point;

        TextView point;
        TextView fund_point;
        TextView rest_point;
        TextView textNick;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            funding = itemView.findViewById(R.id.funding);
            inst = itemView.findViewById(R.id.textTitle);
            inst_desc = itemView.findViewById(R.id.inst_desc);
            img = itemView.findViewById(R.id.imageView2);
            seekBar = itemView.findViewById(R.id.seekBar);
            progressbar = itemView.findViewById(R.id.progressBar2);

            point = itemView.findViewById(R.id.point);
            seekmin = itemView.findViewById(R.id.seek_min);
            seek_max = itemView.findViewById(R.id.seek_max);
            tar_point = itemView.findViewById(R.id.tar_point);
            acu_point = itemView.findViewById(R.id.acu_point);
            left_point = itemView.findViewById(R.id.left_point);
            fund_point = itemView.findViewById(R.id.fund_point);
            rest_point = itemView.findViewById(R.id.rest_point);

            textNick = itemView.findViewById(R.id.user);
            textNick.setText("김유진");

            //final int targ = Integer.valueOf(targ_point.getText().toString());
            Button funding_button = itemView.findViewById(R.id.funding_button);


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    int position = getAdapterPosition();
                    tar_pointt = mData.get(position).tar_point;
                    final int acu_point = mData.get(position).acu_point;
                    final int left_pointt = tar_pointt - acu_point;
                    final int seek_max = min(mytotalPoint, left_pointt);
                    fund_point.setText(String.valueOf(seekBar.getProgress() * (seek_max/10)));
                    fund_pointt = seekBar.getProgress() * (seek_max/10);

                    int rest = mytotalPoint - progress * (seek_max/10);
//                    rest_point.setText(String.valueOf(rest));
                    if(rest >= 0)

                        rest_point.setText(String.valueOf(rest));
                    else
                        rest_point.setText(String.valueOf(0));

                    //fund_point.add(new fundingItemActivity());

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //point.setText("제대로 돌아감");

                }
            });

            funding.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
            itemView.setOnScrollChangeListener( new View.OnScrollChangeListener() {

                @Override
                public void onScrollChange(View view, int i, int i1, int oldl, int oldt) {
                    int position = getAdapterPosition();
                    mCallback.onScroll(mData.get(position));
                }

            });

            funding_button.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onFundingClick(mData.get(position));
                    left_pointt = left_pointt + fund_pointt;
                    pointt = mytotalPoint;
                }
            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    fundingItemAdapter(ArrayList<fundingItem> item, OnItemClickForFunding listener) {
        mData = item ;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public fundingItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.funding_item, parent, false) ;
        fundingItemAdapter.ViewHolder vh = new fundingItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(fundingItemAdapter.ViewHolder holder, int position) {

        String inst = mData.get(position).fund_inst;
        String inst_desc = mData.get(position).inst_des;
        int img = mData.get(position).img;
        pointt = mytotalPoint;
        tar_pointt = mData.get(position).tar_point;
        int acu_point = mData.get(position).acu_point;

        left_pointt = tar_pointt - acu_point;
        int seek_max = min(mytotalPoint, left_pointt);

        holder.inst.setText(inst) ;
        holder.inst_desc.setText(inst_desc);
        holder.img.setImageResource(img);
        holder.point.setText(String.valueOf(pointt));
        holder.seek_max.setText(String.valueOf(seek_max));
        holder.tar_point.setText(String.valueOf(tar_pointt));
        holder.acu_point.setText(String.valueOf(acu_point));
        holder.left_point.setText(String.valueOf(left_pointt));
        holder.rest_point.setText(String.valueOf(pointt));
        holder.progressbar.setMax(tar_pointt);
        holder.progressbar.setProgress(acu_point);

        //holder.point.setText(String.valueOf(point));
//        holder.fund_point.setText(String.valueOf(left_point));
//        holder.rest_point.setText(String.valueOf(left_point));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}
