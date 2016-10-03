package com.example.jiahua.regionbilagdesign.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.jiahua.regionbilagdesign.Logic.Fragmentmanager;
import com.example.jiahua.regionbilagdesign.R;

public class MainActivity extends FragmentActivity {

    private Fragmentmanager fragments = new Fragmentmanager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view);

        if (savedInstanceState == null) {
                     getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragments.getVelkommenfragment())
                    .commit();
        }
    }
}
