package com.example.jiahua.regionbilagdesign.Fragmenter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jiahua.regionbilagdesign.Logic.Fragmentmanager;
import com.example.jiahua.regionbilagdesign.R;

/**
 * Created by Jiahua on 26-09-2016.
 */

public class Velkommen_fragment extends Fragment implements View.OnClickListener {

    private Button UdfyldBilag = null;
    private Fragmentmanager fragments = new Fragmentmanager();

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.velkommen_view, container, false);

        UdfyldBilag = (Button) rod.findViewById(R.id.UdfyldBilag);
        UdfyldBilag.setOnClickListener(this);

        return rod;
    }

    public void onClick(View v) {

        if (v == UdfyldBilag) {
            getFragmentManager().beginTransaction().replace(R.id.container, fragments.getTolkbilag1fragment()).addToBackStack(fragments.getTolkbilag1fragment().getTag()).commit();
        }
    }
}