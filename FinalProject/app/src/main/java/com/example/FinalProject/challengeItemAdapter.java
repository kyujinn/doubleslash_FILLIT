package com.example.FinalProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeItemAdapter extends RecyclerView.Adapter<challengeItemAdapter.ViewHolder> {
    private ArrayList<challengeItem> mData = new ArrayList<challengeItem>();
    private OnItemClickForChallenge mCallback;
    static int mytotalPoint = 0;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        TextView Daterange;
        TextView getPoint;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            Daterange = itemView.findViewById(R.id.Daterange);
            getPoint = itemView.findViewById(R.id.getPoint);
            itemView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    challengeItemAdapter(ArrayList<challengeItem> item, OnItemClickForChallenge listener) {
        mData.clear();
        mData.addAll(item);
        this.mCallback = listener;
    }

    public void changeData(ArrayList<challengeItem> newItems) {
        mData.clear();
        mData.addAll(newItems);
        notifyDataSetChanged();
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public challengeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.challenge_item, parent, false) ;
        challengeItemAdapter.ViewHolder vh = new challengeItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(challengeItemAdapter.ViewHolder holder, int position) {

        String title = mData.get(position).title;
        String description = mData.get(position).des;
        String regdate = mData.get(position).regdate;
        String deadline = mData.get(position).deadline;
        String getDate = regdate + " ~ " + deadline;
        holder.textTitle.setText(title) ;
        holder.textDescription.setText(description);
        holder.Daterange.setText(getDate);
        holder.getPoint.setText(String.valueOf(mData.get(position).days * 100));
        mytotalPoint = mData.get(position).progress * 100;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }




}
