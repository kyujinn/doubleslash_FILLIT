package com.example.FinalProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.FinalProject.challengeItemAdapter.mytotalPoint;

public class challengeMain extends Fragment implements OnItemClickForChallenge{
    RecyclerView viewList;
    challengeItemAdapter adapter;
    private NestedScrollView scrollView;
    TextView textCount;
    TextView totalPoint;
    //private int count = -1;
    //final String[][] contents = new String[5][2];

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_main, container, false);
        viewList = view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
        viewList.setLayoutManager(mLinearLayoutManager);
        textCount = view.findViewById(R.id.todayCount);
        totalPoint = view.findViewById(R.id.totalpoint);

        //aCurrentData.listMyChallenge.clear();
        Bundle extras = getArguments();
        challengeItem newOne = new challengeItem();

        if(extras != null) {
            newOne.title = extras.getString("title");
            newOne.des = extras.getString("des");
            newOne.days = extras.getInt("days");
            newOne.chalPoint = extras.getInt("chalPoint");
            newOne.regdate = extras.getString("regdate");
            newOne.deadline = extras.getString("deadline");

            aCurrentData.listMyChallenge.add(newOne);
        }

        adapter = new challengeItemAdapter(aCurrentData.listMyChallenge, this);
        textCount.setText(aCurrentData.listMyChallenge.size() + " 개");
        totalPoint.setText("나의 포인트 :   " + mytotalPoint + "   포인트");
        viewList.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewList.getContext(),
//                mLinearLayoutManager.getOrientation());
//        viewList.addItemDecoration(dividerItemDecoration);

        scrollView = view.findViewById(R.id.viewScroll);
        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
    }
}
