package com.example.FinalProject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class homeMain extends Fragment {
    private AdView adView;
    wasteItemBannerAdapter adapter2;
    RecyclerView viewList2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);

        MobileAds.initialize(this.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            } });
        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        aCurrentData.listWasteBanner.clear();
        for (int i = 0; i < 4; i++) {
            wasteItemBanner newOne = new wasteItemBanner();
            switch (i) {
                case 0:
                    newOne.img = R.drawable.banner01;
                    break;
                case 1:
                    newOne.img = R.drawable.banner02;
                    break;
                case 2:
                    newOne.img = R.drawable.banner03;
                    break;
                case 3:
                    newOne.img = R.drawable.banner04;
                    break;
            }
            aCurrentData.listWasteBanner.add(newOne);
        }

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        // 좌우 넘기기
        viewList2 = view.findViewById(R.id.recyclerView2);
        viewList2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        // 부드럽게 넘기기
        snapHelper.attachToRecyclerView(viewList2);
        adapter2 = new wasteItemBannerAdapter(aCurrentData.listWasteBanner);
        viewList2.setAdapter(adapter2);

        ConstraintLayout buttonMask = view.findViewById(R.id.buttonMask);
        ConstraintLayout buttonPlastic = view.findViewById(R.id.buttonPlastic);
        ConstraintLayout buttonPaper = view.findViewById(R.id.buttonPaper);
        ConstraintLayout buttonCan = view.findViewById(R.id.buttonCan);
        ConstraintLayout buttonVinyl = view.findViewById(R.id.buttonVinyl);
        ConstraintLayout buttonEtc = view.findViewById(R.id.buttonEtc);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터베이스에서 ctgr 맞는거 불러오기
                switch (view.getId()) {
                    case R.id.buttonMask:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("마스크");
                        break;
                    case R.id.buttonPlastic:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("플라스틱");
                        break;
                    case R.id.buttonPaper:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("종이");
                        break;
                    case R.id.buttonCan:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("캔/유리");
                        break;
                    case R.id.buttonVinyl:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("비닐");
                        break;
                    case R.id.buttonEtc:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("스트로폼");
                        break;
                }
            }
        };

        buttonMask.setOnClickListener(btnListener);
        buttonPlastic.setOnClickListener(btnListener);
        buttonPaper.setOnClickListener(btnListener);
        buttonCan.setOnClickListener(btnListener);
        buttonVinyl.setOnClickListener(btnListener);
        buttonEtc.setOnClickListener(btnListener);

        Button buttonGraph = (Button)view.findViewById(R.id.buttongraph);
        buttonGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GraphMain fragment = new GraphMain();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameMain, fragment).commit();
//                ((interfaceMain)getActivity()).changeFragmentHomeGraph(Fragment.newInstance());
            }
        });
        return view;

    }
}
