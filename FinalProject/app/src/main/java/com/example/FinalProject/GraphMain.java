package com.example.FinalProject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GraphMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_main, container, false);

        ImageView graph1 = (ImageView)view.findViewById(R.id.graph1);
        ImageView graph2 = (ImageView)view.findViewById(R.id.graph2);
        ImageView graph3 = (ImageView)view.findViewById(R.id.graph3);

        // res/drawable 폴더에 있는 이미지로 셋팅하기
        graph1.setImageResource(R.drawable.graph1);
        graph2.setImageResource(R.drawable.graph2);
        graph3.setImageResource(R.drawable.graph3);

        graph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), graph1Activity.class);
                startActivity(intent);
            }
        });

        graph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), graph2Activity.class);
                startActivity(intent);
            }
        });

        graph3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), graph3Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
