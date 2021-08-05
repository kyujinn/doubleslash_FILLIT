package com.example.FinalProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class wasteMain extends Fragment implements OnItemClickForWaste {
    RecyclerView viewList;
    wasteItemAdapter adapter;
    ArrayList<wasteItem> items;
    String ctgr;

    public void setItems(ArrayList<wasteItem> newOne) {
        items = newOne;
    }
    public void setCtgr(String ctgr) {
        this.ctgr = ctgr;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waste_main, container, false);
        TextView textCtgr = view.findViewById(R.id.textCtgr);
        if (items.size() == 0) {
            textCtgr.setText("정보가 없습니다.");
        } else {
            textCtgr.setText(ctgr);

            viewList = view.findViewById(R.id.recyclerView);
            viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

            // 부드럽게 넘기기
            PagerSnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(viewList);

            adapter = new wasteItemAdapter(items, this);
            viewList.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onClick(wasteItem newOne) {

    }
}
