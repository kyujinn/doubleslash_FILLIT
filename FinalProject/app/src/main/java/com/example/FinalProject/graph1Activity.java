package com.example.FinalProject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class graph1Activity extends AppCompatActivity {
    PieChart pieChart;

    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);

        pieChart = (PieChart) findViewById(R.id.piechart);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();
        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "'재질별'");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);
        pieData.setValueTextSize(18f);
        pieChart.animateY(3000);

    }

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(57f, 0));
        entries.add(new BarEntry(33f, 1));
        entries.add(new BarEntry(24f, 2));
        entries.add(new BarEntry(15f, 3));
        entries.add(new BarEntry(4f, 4));

    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("플라스틱");
        PieEntryLabels.add("스트로폼");
        PieEntryLabels.add("유리");
        PieEntryLabels.add("비닐");
        PieEntryLabels.add("금속");

    }
}
