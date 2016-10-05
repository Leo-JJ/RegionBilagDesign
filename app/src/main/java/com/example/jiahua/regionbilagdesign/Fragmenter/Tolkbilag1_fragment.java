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

public class Tolkbilag1_fragment extends Fragment implements View.OnClickListener {

    private Button next = null;
    private Fragmentmanager fragments = new Fragmentmanager();

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.tolkbilag1_view, container, false);

        next = (Button) rod.findViewById(R.id.next);
        next.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            getFragmentManager().beginTransaction().replace(R.id.container, fragments.getTolkbilag2fragment()).addToBackStack(fragments.getTolkbilag2fragment().getTag()).commit();
        }
    }
}
