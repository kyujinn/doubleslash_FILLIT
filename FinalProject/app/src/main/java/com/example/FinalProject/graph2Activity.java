package com.example.FinalProject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

//MPAndroidChart import

public class graph2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);
        setTitle("연도별 생활폐기물 발생량");
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(105f, 0));
        entries.add(new Entry(97f, 1));
        entries.add(new Entry(102f, 2));
        entries.add(new Entry(105f, 3));
        entries.add(new Entry(99f, 4));
        entries.add(new Entry(102f, 5));
        entries.add(new Entry(98f, 6));
        entries.add(new Entry(95f, 7));
        entries.add(new Entry(90f, 8));
        entries.add(new Entry(97f, 10));
        entries.add(new Entry(110f, 11));

        LineDataSet dataset = new LineDataSet(entries, "생활폐기물");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("07년");
        labels.add("08년");
        labels.add("09년");
        labels.add("10년");
        labels.add("11년");
        labels.add("12년");
        labels.add("13년");
        labels.add("14년");
        labels.add("15년");
        labels.add("16년");
        labels.add("17년");
        labels.add("18년");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        lineChart.setData(data);
        lineChart.animateY(3000);
    }
}

