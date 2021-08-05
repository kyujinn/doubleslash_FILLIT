package com.example.FinalProject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class interfaceMain extends AppCompatActivity {
    FragmentManager fragmentManager = getFragmentManager();

    ImageView imageHome;
    ImageView imageEnroll;
    ImageView imageChallenge;
    ImageView imageFunding;

    TextView textHome;
    TextView textEnroll;
    TextView textChallenge;
    TextView textFunding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_main);

        // 잡다한 변수들
        {
            ConstraintLayout buttonHome = findViewById(R.id.menuHome);
            ConstraintLayout buttonChallenge = findViewById(R.id.menuChallenge);
            ConstraintLayout buttonFunding = findViewById(R.id.menuFunding);
            ConstraintLayout buttonEnroll = findViewById(R.id.menuEnroll);

            imageHome = findViewById(R.id.imageHome);
            imageChallenge = findViewById(R.id.imageChallenge);
            imageFunding = findViewById(R.id.imageFunding);
            imageEnroll = findViewById(R.id.imageEnroll);

            textHome = findViewById(R.id.textHome);
            textChallenge = findViewById(R.id.textChallenge);
            textFunding = findViewById(R.id.textFunding);
            textEnroll = findViewById(R.id.textEnroll);
            callMenu(R.id.menuHome);

            View.OnClickListener btnListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callMenu(view.getId());
                }
            };
            buttonHome.setOnClickListener(btnListener);
            buttonChallenge.setOnClickListener(btnListener);
            buttonEnroll.setOnClickListener(btnListener);
            buttonFunding.setOnClickListener(btnListener);
        }
    }

    public void clearMenu() {
        imageHome.setImageResource(R.drawable.home_default);
        textHome.setTextColor(Color.parseColor("#EEEEEE"));
        imageEnroll.setImageResource(R.drawable.enroll_default);
        textEnroll.setTextColor(Color.parseColor("#EEEEEE"));
        imageChallenge.setImageResource(R.drawable.map_default);
        textChallenge.setTextColor(Color.parseColor("#EEEEEE"));
        imageFunding.setImageResource(R.drawable.funding_default);
        textFunding.setTextColor(Color.parseColor("#EEEEEE"));
    }
    public void callMenu(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.menuHome:
                clearMenu();
                imageHome.setImageResource(R.drawable.home_on);
                textHome.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new homeMain());
                break;
            case R.id.menuEnroll:
                clearMenu();
                imageEnroll.setImageResource(R.drawable.enroll_on);
                textEnroll.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new challengeEnrollCtgr());
                break;
            case R.id.menuChallenge:
                clearMenu();
                imageChallenge.setImageResource(R.drawable.map_on);
                textChallenge.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new challengeMain());
                break;
            case R.id.menuFunding:
                clearMenu();
                imageFunding.setImageResource(R.drawable.funding_on);
                textFunding.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new fundingMain());
                break;
        }
        fragmentTransaction.commit();

    }

    public void changeFragmentWasteCtgr(String ctgr) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteMain newPage = new wasteMain();
        newPage.setCtgr(ctgr);
        ArrayList<wasteItem> newOne = new ArrayList<wasteItem>();
        switch (ctgr) {
            case "마스크":
                wasteItem newWaste1 = new wasteItem();
                newWaste1.img = R.drawable.ctgr1;
                newOne.add(newWaste1);
                break;
            case "플라스틱":
                wasteItem newWaste2 = new wasteItem();
                newWaste2.img = R.drawable.ctgr2;
                newOne.add(newWaste2);
                break;
            case "캔/유리":
                wasteItem newWaste3 = new wasteItem();
                newWaste3.img = R.drawable.ctgr3;
                newOne.add(newWaste3);
                break;
            case "종이":
                wasteItem newWaste4 = new wasteItem();
                newWaste4.img = R.drawable.ctgr4;
                newOne.add(newWaste4);
                break;
            case "비닐":
                wasteItem newWaste5 = new wasteItem();
                newWaste5.img = R.drawable.ctgr5;
                newOne.add(newWaste5);
                break;
            case "스트로폼":
                wasteItem newWaste6 = new wasteItem();
                newWaste6.img = R.drawable.ctgr6;
                newOne.add(newWaste6);
                break;
        }
        newPage.setItems(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 등록 세부페이지 내꺼
    public void changeFragmentChallengeEnrollItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeEnrollItemSpecific newPage = new challengeEnrollItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }
    // 챌린지 세부페이지 내꺼
    public void changeFragmentChallengeItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecific newPage = new challengeItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

}
