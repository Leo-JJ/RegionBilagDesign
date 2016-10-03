package com.example.jiahua.regionbilagdesign.Fragmenter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jiahua.regionbilagdesign.Logic.Fragmentmanager;
import com.example.jiahua.regionbilagdesign.R;

/**
 * Created by Jiahua on 27-09-2016.
 */

public class Tolkbilag3_fragment extends Fragment implements View.OnClickListener {

    private Spinner evaType1, evaType2, evaType3, evaType4;
    private Fragmentmanager fragments = new Fragmentmanager();
    private Button next;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod=i.inflate(R.layout.tolkbilag3_view,container,false);

        next = (Button) rod.findViewById(R.id.next);
        next.setOnClickListener(this);

        evaType1 = (Spinner) rod.findViewById(R.id.evaType1);
        evaType2 = (Spinner) rod.findViewById(R.id.evaType2);
        evaType3 = (Spinner) rod.findViewById(R.id.evaType3);
        evaType4 = (Spinner) rod.findViewById(R.id.evaType4);

        String[] eva1 = new String[] {
                "God",
                "Mindre god"
        };

        String[] eva2 = new String[] {
                "God",
                "Mindre god"
        };

        String[] eva3 = new String[] {
                "Ja",
                "Nej"
        };

        String[] eva4 = new String[] {
                "Ja",
                "Nej"
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, eva1);
        evaType1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, eva2);
        evaType2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, eva3);
        evaType3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, eva4);
        evaType4.setAdapter(adapter4);

        return rod;
        }

    @Override
    public void onClick(View v) {
        if(v == next){
            getFragmentManager().beginTransaction().replace(R.id.container, fragments.getLaegeunderskriftfragment()).addToBackStack(fragments.getLaegeunderskriftfragment().getTag()).commit();
        }
    }
}