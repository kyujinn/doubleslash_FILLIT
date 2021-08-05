package com.example.FinalProject;

import java.util.ArrayList;

import static java.lang.Math.min;

public class fundingItem {
    String fund_inst;
    String inst_des;
    int img;
    int seek_max;
    int tar_point; // 펀딩 목표 포인트
    int acu_point; // 펀딩 전체에서 누적 포인트
    int left_point; // 펀딩 전체에서 달성까지 남은 포인트
    int fund_point;
    int mypoint;
    int rest_point;

    public ArrayList<fundingItem> init() {
        fund_inst = "";
        inst_des = "";

        tar_point = 10000;
        acu_point = 9000;
        mypoint = 500;

        left_point = tar_point - acu_point;

        seek_max = min(mypoint, left_point);
        fund_point = 0;
        rest_point = mypoint;

        return null;
    }
}
