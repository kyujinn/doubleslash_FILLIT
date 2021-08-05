package com.example.FinalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class challengeItem {
    String title;
    String des;
    String regdate;
    String deadline;
    int chalPoint = 100;
    Set<Long> days1 = new TreeSet<>();
    int days = 0;
    static int progress = 0;
    int mytotalPoint;
    ArrayList<challengeItemActivity> acvts = new ArrayList<challengeItemActivity>();

    public void setFromFrame(challengeFrameItem item) {
        //chalId = item.chalfrId;
        title = item.title;
        des = item.des;
        chalPoint = item.point;
        regdate = item.regdate;
        deadline = item.deadline;
    }

//    public void setDates(List<Calendar> selectedDates) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        regdate = sdf.format(selectedDates.get(0).getTime());
//        deadline = sdf.format(selectedDates.get(selectedDates.size() - 1).getTime());
//        for (int i = 0; i < selectedDates.size(); i++) {
//            days.add(selectedDates.get(i).getTimeInMillis());
//        }
//    }

    public void clone(challengeItem original) {
        //chalId = original.chalId;
        title = original.title;
        des = original.des;
        regdate = original.regdate;
        deadline = original.deadline;
        days = original.days;
        days1.addAll(original.days1);
        chalPoint= original.chalPoint;
        acvts = original.acvts;
    }

    public void setDates(List<Calendar> selectedDates) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        regdate = sdf.format(selectedDates.get(0).getTime());
        deadline = sdf.format(selectedDates.get(selectedDates.size() - 1).getTime());
        for (int i = 0; i < selectedDates.size(); i++) {
            days1.add(selectedDates.get(i).getTimeInMillis());
        }
    }

    public void setDatesFromServer() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        try {
            start.setTime(sdf.parse(regdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar temp = Calendar.getInstance();
        try {
            temp.setTime(sdf.parse(regdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar end = Calendar.getInstance();
        try {
            end.setTime(sdf.parse(deadline));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= end.get(Calendar.DATE) - start.get(Calendar.DATE); i++ ) {
            days1.add(temp.getTimeInMillis());
            temp.add(temp.DATE, 1);
            acvts.add(new challengeItemActivity());
        }
    }

}
