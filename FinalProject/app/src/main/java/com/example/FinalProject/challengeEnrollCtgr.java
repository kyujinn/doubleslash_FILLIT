package com.example.FinalProject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class challengeEnrollCtgr extends Fragment {
    private GridViewAdapter mAdapter;
    private ArrayList<String> listCategory;
    private ArrayList<Integer> listPicture;
    private GridView gridView;
    private AdView adView;

    static int num = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_enroll_ctgr, container, false);

        MobileAds.initialize(this.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            } });
        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        prepareList();
        // prepared arraylist and passed it to the Adapter class
        mAdapter = new GridViewAdapter(getActivity(), listCategory, listPicture);
        // Set custom adapter to gridview
        gridView = (GridView) view.findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getActivity(), mAdapter.getItem(position), Toast.LENGTH_SHORT).show();

                if(mAdapter.getItem(position) == "마스크")
                    num = 1;
                if(mAdapter.getItem(position) == "플라스틱")
                    num = 2;
                if(mAdapter.getItem(position) == "캔 / 유리")
                    num = 3;
                if(mAdapter.getItem(position) == "종이")
                    num = 4;
                if(mAdapter.getItem(position) == "비닐")
                    num = 5;
                if(mAdapter.getItem(position) == "기타")
                    num = 6;

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameMain, new challengeEnrollMain()).commit();

//                Intent intent = new Intent(getApplicationContext(), challengeList.class);
//                startActivity(intent);
            }
        });
        return view;
    }

    public void prepareList() {
        listCategory = new ArrayList<String>();

        listCategory.add("마스크");
        listCategory.add("플라스틱");
        listCategory.add("캔 / 유리");
        listCategory.add("종이");
        listCategory.add("비닐");
        listCategory.add("기타");

        listPicture = new ArrayList<Integer>();
        listPicture.add(R.drawable.category1);
        listPicture.add(R.drawable.category2);
        listPicture.add(R.drawable.category3);
        listPicture.add(R.drawable.category4);
        listPicture.add(R.drawable.category5);
        listPicture.add(R.drawable.category6);
    }
}
