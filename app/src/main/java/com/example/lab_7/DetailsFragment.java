package com.example.lab_7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class DetailsFragment extends Fragment {
    private String data ;
    private TextView fillMe1, fillMe2, fillMe3;
    private String mParam1;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public DetailsFragment(String data){
        this.data = data;
    }

    public void setFillMe(){
        fillMe1 = getView().findViewById(R.id.fillMe_1);
        fillMe2 = getView().findViewById(R.id.fillMe_2);
        fillMe3 = getView().findViewById(R.id.fillMe_3);

        fillMe1.setText(data.split(",")[0]);
        fillMe2.setText(data.split(",")[1]);
        fillMe3.setText(data.split(",")[2]);
        System.out.println("Done");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_details, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFillMe();
    }


}