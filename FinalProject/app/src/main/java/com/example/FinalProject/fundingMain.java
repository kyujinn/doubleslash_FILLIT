package com.example.FinalProject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import static com.example.FinalProject.challengeItemAdapter.mytotalPoint;
import static com.example.FinalProject.fundingItemAdapter.fund_pointt;

public class fundingMain extends Fragment implements OnItemClickForFunding{
    RecyclerView viewList;
    fundingItemAdapter adapter;

    //    ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
//    TextView funding = viewList.findViewById(R.id.funding);
//    TextView fundingTitle = viewList.findViewById(R.id.fundingTitle);
//    TextView fundingSpecific = viewList.findViewById(R.id.fundingSpecific);
//    ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);
    TextView pointSpent;
    TextView pointRest;
    TextView myPoint;
    TextView acu_point;
    TextView fund_point;
//    int mypoint = mData.get(position).progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_main, container,false);

        aCurrentData.listFunding.clear();
        for (int i = 0; i < 3; i++) {
            fundingItem newOne = new fundingItem();
            switch (i) {
                case 0:
                    newOne.fund_inst = "그린피스";
                    newOne.img = (R.drawable.greenpeace);
                    newOne.inst_des = "그린피스는 1971년 태어난 독립적인 국제환경단체로 지구 환경보호와 평화를 위해 비폭력 직접행동의 평화적인 방식으로 캠페인을 진행하고 있습니다.";
                    newOne.tar_point = 50000;
                    newOne.acu_point = 11000 + fund_pointt;
                    break;
                case 1:
                    newOne.fund_inst = "WWF";
                    newOne.img = R.drawable.wwf;
                    newOne.inst_des = "WWF는 지구상의 다양한 생명체와 이들이 서식하는 아름다운 자연환경을 보전하는 일을 하고 있습니다.이와 함께 인류가 동식물과 자연환경에 미치는 영향을 줄이는 데 힘쓰고 있습니다";
                    newOne.tar_point = 10000;
                    newOne.acu_point = 9500 + fund_pointt;
                    break;
                case 2:
                    newOne.fund_inst = "생명의 숲";
                    newOne.img = R.drawable.forest;
                    newOne.inst_des = "생명의 숲은 시민의 힘으로 나무를 심고 숲을 가꾸고 보전하며, 숲의 공공성을 높여 누구나 숲의 가치를 누리는 건강한 사회를 만들어 가고자 합니다. ";
                    newOne.tar_point = 200000;
                    newOne.acu_point = 64000 + fund_pointt;
                    break;

            }
            aCurrentData.listFunding.add(newOne);
        }

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);

        return view;

    }

    @Override
    public void onClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);

        TextView funding = viewList.findViewById(R.id.funding);
        TextView inst_desc = viewList.findViewById(R.id.inst_desc);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        funding_list.startAnimation(anim);

        inst_desc.setVisibility(View.GONE);
        funding.setVisibility(View.GONE);

        funding_list.setVisibility(View.VISIBLE);

        //funding_list.setBackgroundColor(Color.parseColor("#79C3A0"));
    }

    @Override
    public void onScroll(fundingItem newOne) {

        TextView textNowPoint = viewList.findViewById(R.id.textNowPoint);
        TextView textPoint = viewList.findViewById(R.id.textPoint);
        TextView textRestPoint = viewList.findViewById(R.id.textRestPoint);

        textNowPoint.setVisibility(View.VISIBLE);
        textPoint.setVisibility(View.VISIBLE);
        textRestPoint.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);
    }

    @Override
    public void onFundingClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
        ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);
        pointSpent = viewList.findViewById(R.id.fund_point);
        pointRest = viewList.findViewById(R.id.rest_point);
        myPoint = viewList.findViewById(R.id.point);
        acu_point = viewList.findViewById(R.id.acu_point);
        fund_point = viewList.findViewById(R.id.fund_point);

        int accu = Integer.parseInt((acu_point.getText().toString())) + Integer.parseInt((pointSpent.getText().toString()));
        newOne.acu_point = accu;

        fundingItemActivity newActivity = new fundingItemActivity();
        newActivity.point = Integer.parseInt((pointSpent.getText().toString()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        newActivity.funddate = sdf.format(Calendar.getInstance().getTime());
        newActivity.acu_point = accu;

        mytotalPoint = Integer.parseInt((pointRest.getText().toString()));
        point newpointActivity = new point();
        newpointActivity.point = Integer.parseInt((pointRest.getText().toString()));


        funding_list.setVisibility(View.GONE);
        funding_click.setVisibility(View.VISIBLE);
//        funding_click.setBackgroundColor(Color.parseColor("#79C3A0"));

    }

}