package com.example.FinalProject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.FinalProject.challengeEnrollCtgr.num;

public class challengeEnrollMain extends Fragment implements OnItemClickForChallenge{

    RecyclerView viewList;
    challengeEnrollItemAdapter adapter;
    ArrayList<challengeItem> challengeitemlist;
    Button plusbutton;
    String strTitle;
    String strSubtitle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_enroll_main, container, false);
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        InitializeChallengeData();

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new challengeEnrollItemAdapter(aCurrentData.listChallengeEnroll, this);
        viewList.setAdapter(adapter);

        plusbutton = view.findViewById(R.id.plusbutton);
        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(challengeEnrollMain.this.getContext());

                View view2 = LayoutInflater.from(challengeEnrollMain.this.getContext())
                        .inflate(R.layout.edit_box, null, false);
                builder.setView(view2);

                final Button buttonsubmit = (Button) view2.findViewById(R.id.button_dialog_submit);
                final EditText editTextTitle = (EditText) view2.findViewById(R.id.edittext_dialog_title);
                final EditText editTextSubtitle = (EditText) view2.findViewById(R.id.edittext_dialog_subtitle);

                final AlertDialog dialog = builder.create();
                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
                buttonsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 4. 사용자가 입력한 내용을 가져와서
                        strTitle = editTextTitle.getText().toString();
                        strSubtitle = editTextSubtitle.getText().toString();

                        challengeItem newOne = new challengeItem();
                        newOne.title = strTitle;
                        newOne.des = strSubtitle;
                        aCurrentData.listChallengeEnroll.add(newOne);
                        //InitializeChallengeData();
//                        // 5. ArrayList에 추가하고
//                        Dictionary dict = new Dictionary(strTitle, strSubtitle );
//                        mArrayList.add(0, dict); //첫번째 줄에 삽입됨
//                        //mArrayList.add(dict); //마지막 줄에 삽입됨
//
//                        // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
//                        mAdapter.notifyItemInserted(0);
//                        //mAdapter.notifyDataSetChanged();
//                        aCurrentData.listChallengeEnroll.add(newOne);

                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeEnrollItemSpecific(newOne);
    }

    public void InitializeChallengeData()
    {
        if(num == 1)
        {
            aCurrentData.listChallengeEnroll.clear();
            challengeItem newOne = new challengeItem();
            newOne.title = "마스크 분리배출";
            newOne.des = "마스크 일반쓰레기통에 잘 버리기";
            aCurrentData.listChallengeEnroll.add(newOne);
        }
        if(num == 2)
        {
            aCurrentData.listChallengeEnroll.clear();
            for (int i = 0; i < 4; i++) {
                challengeItem newOne = new challengeItem();
                switch (i) {
                    case 0:
                        newOne.title = "플라스틱 분리배출";
                        newOne.des = "내용물을 비우고 물로 헹궈 이물질 제거 후 배출하기";
                        break;
                    case 1:
                        newOne.title = "플라스틱 사용하지 않기";
                        newOne.des = "배달 음식 이용 시 일회용품 받지 않기";
                        break;
                    case 2:
                        newOne.title = "일회용 컵 사용하지 않기";
                        newOne.des = "카페 이용시 텀블러 사용하기";
                        break;
                    case 3:
                        newOne.title = "일회용 빨대 사용하지 않기";
                        newOne.des = "재사용 가능한 빨대 사용하기";
                        break;
                }
                aCurrentData.listChallengeEnroll.add(newOne);
            }
        }
        if(num == 3)
        {
            aCurrentData.listChallengeEnroll.clear();
            for (int i = 0; i < 3; i++) {
                challengeItem newOne = new challengeItem();
                switch (i) {
                    case 0:
                        newOne.title = "유리/캔 분리배출";
                        newOne.des = "내용물을 비우고 물로 헹궈 이물질 제거 후 배출하기";
                        break;
                    case 1:
                        newOne.title = "유리/캔 분리배출";
                        newOne.des = "상표 제거 가능한 경우 상표를 제거한 후 배출";
                        break;
                    case 2:
                        newOne.title = "유리/캔 분리배출";
                        newOne.des = "깨지지 않은 유리를 버리기";
                        break;
                }
                aCurrentData.listChallengeEnroll.add(newOne);
            }
        }
        if(num == 4)
        {
            aCurrentData.listChallengeEnroll.clear();
            challengeItem newOne = new challengeItem();
            newOne.title = "종이 분리배출";
            newOne.des = "비닐 코팅된 표지, 공책의 스프링 등을 제거한 후 배출하기";
            aCurrentData.listChallengeEnroll.add(newOne);
        }
        if(num == 5)
        {
            aCurrentData.listChallengeEnroll.clear();
            challengeItem newOne = new challengeItem();
            newOne.title = "비닐 분리배출";
            newOne.des = "내용물을 비우고 물로 헹궈 이물질 제거 후 배출하기";
            aCurrentData.listChallengeEnroll.add(newOne);
        }
        if(num == 6)
        {
            aCurrentData.listChallengeEnroll.clear();
            challengeItem newOne = new challengeItem();
            newOne.title = "대중교통 이용하기";
            newOne.des = "자가용 이용하지 않기";
            aCurrentData.listChallengeEnroll.add(newOne);
        }
    }
}
