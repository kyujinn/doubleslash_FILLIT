package com.example.FinalProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class wasteItemBannerAdapter extends RecyclerView.Adapter<wasteItemBannerAdapter.ViewHolder>{

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBanner;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            imageBanner = itemView.findViewById(R.id.imageBanner);

            itemView.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    wasteItemBannerAdapter(ArrayList<wasteItemBanner> item) {
        aCurrentData.listWasteBanner = item ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public wasteItemBannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.waste_banner_item, parent, false) ;
        wasteItemBannerAdapter.ViewHolder vh = new wasteItemBannerAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(wasteItemBannerAdapter.ViewHolder holder, int position) {
        holder.imageBanner.setImageResource(aCurrentData.listWasteBanner.get(position).img);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return aCurrentData.listWasteBanner.size() ;
    }
}
