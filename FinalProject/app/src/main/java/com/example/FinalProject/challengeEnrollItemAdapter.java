package com.example.FinalProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeEnrollItemAdapter extends RecyclerView.Adapter<challengeEnrollItemAdapter.ViewHolder> {

    private ArrayList<challengeItem> mData = null ;
    private OnItemClickForChallenge mCallback;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    challengeEnrollItemAdapter(ArrayList<challengeItem> item, OnItemClickForChallenge listener) {
        mData = item ;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public challengeEnrollItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.challenge_enroll_item, parent, false) ;
        challengeEnrollItemAdapter.ViewHolder vh = new challengeEnrollItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(challengeEnrollItemAdapter.ViewHolder holder, int position) {
        String title = mData.get(position).title;
        String description = mData.get(position).des;
        String regdate = mData.get(position).regdate;
        String deadline = mData.get(position).deadline;
        holder.textTitle.setText(title) ;
        holder.textDescription.setText(description);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}

