package com.example.FinalProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.FinalProject.challengeItemAdapter.mytotalPoint;

public class challengeComplete extends Fragment {

    String title;
    String des;
    static int point;
    static int totalpoint;
    String regdate;
    String deadline;
    challengeItem item = new challengeItem();
    Button complete;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_complete, container, false);

        Bundle extras = getArguments();
//        challengeItem newOne = new challengeItem();

        if(extras != null) {
            title = extras.getString("title", "0");
            des = extras.getString("des", "0");
            point = extras.getInt("chalPoint", 0);
            regdate = extras.getString("regdate", "0");
            deadline = extras.getString("deadline", "0");

//            aCurrentData.listMyChallenge.add(newOne);
        }

        TextView Title1 = (TextView) view.findViewById(R.id.textTitle);
        TextView description1 = (TextView) view.findViewById(R.id.textDescription);
        TextView textPointDay = (TextView) view.findViewById(R.id.textReward);
        TextView textReward = (TextView) view.findViewById(R.id.textPointTotal);
        TextView textStartDate = (TextView) view.findViewById(R.id.textStartDate);
        TextView textEndDate = (TextView) view.findViewById(R.id.textEndDate);
        complete = (Button)view.findViewById(R.id.complete);

        Title1.setText(title);
        description1.setText(des);
        textPointDay.setText(String.valueOf(point));
        textReward.setText(String.valueOf(mytotalPoint + point));
        textStartDate.setText(regdate);
        textEndDate.setText(deadline);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).callMenu(R.id.menuHome);
            }
        });

        return view;
    }
}
