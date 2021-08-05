package com.example.FinalProject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class graph3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph3);

        setTitle("연도별 폐기물 처리량");
        BarChart chart = findViewById(R.id.barchart);

        ArrayList entries = new ArrayList();

        entries.add(new BarEntry(945f, 0));
        entries.add(new BarEntry(1040f, 1));
        entries.add(new BarEntry(1133f, 2));
        entries.add(new BarEntry(1240f, 3));
        entries.add(new BarEntry(1369f, 4));
        entries.add(new BarEntry(1487f, 5));
        entries.add(new BarEntry(1501f, 6));
        entries.add(new BarEntry(1645f, 7));
        entries.add(new BarEntry(1578f, 8));
        entries.add(new BarEntry(1695f, 9));
        entries.add(new BarEntry(1472f, 10));
        entries.add(new BarEntry(1592f, 11));

        ArrayList day = new ArrayList();
        day.add("07년");
        day.add("08년");
        day.add("09년");
        day.add("10년");
        day.add("11년");
        day.add("12년");
        day.add("13년");
        day.add("14년");
        day.add("15년");
        day.add("16년");
        day.add("17년");
        day.add("18년");

        BarDataSet bardataset = new BarDataSet(entries, "폐기물처리량");

        BarData data = new BarData(day, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
        chart.animateY(3000);

    }
}
