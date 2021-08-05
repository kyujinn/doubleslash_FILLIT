package com.example.FinalProject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.OrientationHelper;

import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

//import com.example.midleproject.challengeMain.challengeItem;

public class challengeEnrollItemSpecific extends Fragment {
    static challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;
    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }

    static int totalPoint = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.challenge_enroll_specific, container,false);
        final TextView textTitle = view.findViewById(R.id.textTitle);
        final TextView textDescription = view.findViewById(R.id.textDescription);
        final TextView textPointDay = view.findViewById(R.id.textPointDay);
        final TextView enrolldate = view.findViewById(R.id.enrolldate);
        textPointTotal = view.findViewById(R.id.textPointTotal);

//        final ImageView imageEnroll = view.findViewById(R.id.imageEnroll);
//        final TextView textEnroll = view.findViewById(R.id.textEnroll);
//        final ImageView imageChallenge = view.findViewById(R.id.imageChallenge);
//        final TextView textChallenge = view.findViewById(R.id.textChallenge);

        textPointDay.setText(String.valueOf(item.chalPoint));
        textTitle.setText(item.title);
        textDescription.setText(item.des);

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
        calendarView.setSelectionType(SelectionType.RANGE);
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);
        calendarView.setWeekendDayTextColor(Color.RED);
        calendarView.setSelectionManager(new RangeSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                textPointTotal.setText(String.valueOf(item.chalPoint * calendarView.getSelectedDates().size()));
                final List<Calendar> days = calendarView.getSelectedDates();
                for( int i=0; i<days.size(); i++)
                {
                    Calendar calendar = days.get(i);
                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                    final int month = calendar.get(Calendar.MONTH);
                    final int year = calendar.get(Calendar.YEAR);
                    String week = new SimpleDateFormat("EE").format(calendar.getTime());
                    if(i == 0)
                    {
                        String day_full = year + "년 "+ (month+1)  + "월 " + day + "일 " + week + "요일";
                        item.regdate = day_full;
                    }
                    if(i == days.size()-1)
                    {
                        String day_full = year + "년 "+ (month+1)  + "월 " + day + "일 " + week + "요일";
                        item.deadline = day_full;
                    }

                }
                enrolldate.setText(item.regdate + " ~ " + item.deadline);
            }
        }));


//        final ImageView imageEnroll = view.findViewById(R.id.imageEnroll);
//        final TextView textEnroll = view.findViewById(R.id.textEnroll);
//        final ImageView imageChallenge = view.findViewById(R.id.imageChallenge);
//        final TextView textChallenge = view.findViewById(R.id.textChallenge);

        Button buttonEnroll = view.findViewById(R.id.buttonEnroll);
        buttonEnroll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //item.setDates(calendarView.getSelectedDates());
                item.chalPoint = item.chalPoint * calendarView.getSelectedDates().size();

                String title = textTitle.getText().toString();
                String des = textDescription.getText().toString();
                //int days1 = calendarView.getSelectedDates().size();
                int chalPoint = calendarView.getSelectedDates().size() * 100;

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                challengeMain newPage = new challengeMain();
                Bundle extras = new Bundle();
                extras.putString("title", title);
                extras.putString("des", des);
                extras.putInt("days", calendarView.getSelectedDates().size());
                extras.putInt("chalPoint", chalPoint);
                extras.putString("regdate", item.regdate);
                extras.putString("deadline", item.deadline);

                //aCurrentData.listMyChallenge.add(item);

                String result="";
                result = "title : " + title + ", des : " + des + ", days : " + calendarView.getSelectedDates().size() + ", chalPoint : " + chalPoint + ", regdate : " + item.regdate + ", deadline : " + item.deadline;
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

                newPage.setArguments(extras);


//                imageEnroll.setImageResource(R.drawable.enroll_default);
//
//                textEnroll.setTextColor(Color.parseColor("#EEEEEE"));
//
//                imageChallenge.setImageResource(R.drawable.map_on);
//
//                textChallenge.setTextColor(Color.parseColor("#6A6A6A"));
                ((interfaceMain)getActivity()).callMenu(R.id.menuChallenge);
                fragmentTransaction.replace(R.id.frameMain, newPage);
                //((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
                fragmentTransaction.commit(); //저장해라 commit


            }
        });

        return view;
    }

}
