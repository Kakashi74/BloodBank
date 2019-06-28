package com.shanaptech.bloodbank.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanaptech.bloodbank.R;

public class HomeFragment extends Fragment  {

    TabLayout tabLayout ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment , container , false);

           tabLayout = v.findViewById(R.id.tab);
           tabLayout.addTab(tabLayout.newTab().setText(R.string.articles));
           tabLayout.addTab(tabLayout.newTab().setText(R.string.Donation_Requests));
           tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        return v;}
}
