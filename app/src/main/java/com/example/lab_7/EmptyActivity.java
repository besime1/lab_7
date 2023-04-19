package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class EmptyActivity extends AppCompatActivity {

    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        data = getIntent().getExtras();
        DetailsFragment detailsFragment = new DetailsFragment(data.getString("itemData"));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, detailsFragment);
        fragmentTransaction.commitNow();
    }

}