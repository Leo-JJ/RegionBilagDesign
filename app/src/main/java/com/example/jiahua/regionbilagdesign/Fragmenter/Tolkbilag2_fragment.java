package com.example.jiahua.regionbilagdesign.Fragmenter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import com.example.jiahua.regionbilagdesign.Logic.Fragmentmanager;
import com.example.jiahua.regionbilagdesign.R;

/**
 * Created by Jiahua on 26-09-2016.
 */

public class Tolkbilag2_fragment extends Fragment implements Datovaelger_fragment.OnDateRangeSelectedListener, Tidsvaelger_fragment.OnTimeRangeSelectedListener {

    private Spinner Tolkningtype, Ydelsesomfang, Ydelsestype;
    private TextView textView;
    private EditText dato, fratid, tiltid, sprog;
    private boolean sluttidkun = false;
    private Fragmentmanager fragments = new Fragmentmanager();
    private Button next = null;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.tolkbilag2_view, container, false);
        next = (Button) rod.findViewById(R.id.next);

        Tolkningtype = (Spinner) rod.findViewById(R.id.Tolkningtype);
        Ydelsesomfang = (Spinner) rod.findViewById(R.id.Ydelsensomfang);
        Ydelsestype = (Spinner) rod.findViewById(R.id.Ydelsenstype);
        textView = (TextView)rod.findViewById(R.id.textView2);
        sprog = (EditText) rod.findViewById(R.id.Sprog);
        dato = (EditText) rod.findViewById(R.id.Dato);
        fratid = (EditText) rod.findViewById(R.id.Fratid);
        tiltid = (EditText) rod.findViewById(R.id.Tiltid);


        String[] tolkforbindelse = new String[] {
                "Ambulant besøg",
                "Førstegangstolkning under ét indlæggelsesforløb",
                "Senere tolkning under ét índlæggelsesfprløb"
        };
        final int[] val1 = { 0, 1, 2};

        String[] ydelsensomfang = new String[] {
                "Planlagt tolkning 08-17 hverdage",
                "Planlagt tolkning 17-08 hverdage",
                "Akuttolkning 08-17 hverdage",
                "Akuttolkning 17-08 hverdage",
                "Akuttolkning lør/søn/helligdage",
                "Patienten udeblevet",
                "Tolken udeblevet",
                "Tolkning aflyst indenfor 12 timer"
        };
        String[] ydelsenstype = new String[] {
                "Konsultation",
                "Telefonkonsultation",
                "Webcamtolkning"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tolkforbindelse);
        Tolkningtype.setAdapter(adapter);
        Tolkningtype.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s1 = String.valueOf(val1[position]);
                textView.setText(s1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ydelsensomfang);
        Ydelsesomfang.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ydelsenstype);
        Ydelsestype.setAdapter(adapter2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == next) {
                    getFragmentManager().beginTransaction().replace(R.id.container, fragments.getTolkbilag3fragment()).addToBackStack(fragments.getTolkbilag3fragment().getTag()).commit();
                }
            }
        });
        dato.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Datovaelger_fragment dateRangePickerFragment = Datovaelger_fragment.newInstance(Tolkbilag2_fragment.this, true, 0);
                    dateRangePickerFragment.show(getFragmentManager(), "datePicker");
                }}
        });
        fratid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Tidsvaelger_fragment PickerFragment = Tidsvaelger_fragment.newInstance(Tolkbilag2_fragment.this, true, 0);
                    PickerFragment.show(getFragmentManager(), "datePicker");
                    sluttidkun = false;
                }
            }
        });

        tiltid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    Tidsvaelger_fragment PickerFragment = Tidsvaelger_fragment.newInstance(Tolkbilag2_fragment.this, true,1);
                    PickerFragment.show(getFragmentManager(), "datePicker");
                    sluttidkun=true;
                }
            }
        });

        return rod;
    }

    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        if(startDay>9) {
            if((startMonth + 1) >9) {
                dato.setText(startDay + "-" + (startMonth + 1) + "-" + startYear);
            }
            else{
                dato.setText(startDay + "-" +"0"+(startMonth + 1) + "-" + startYear);
            }
        }
        else{
            if((startMonth + 1) >9) {
                dato.setText("0" + startDay + "-" + (startMonth + 1) + "-" + startYear);
            }
            else{
                dato.setText("0" + startDay + "-" + "0"+(startMonth + 1) + "-" + startYear);
            }
        }
    }

    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {
        String time1=String.valueOf(startHour),time2=String.valueOf(endHour),min1=String.valueOf(startMin),min2=String.valueOf(endMin);
        if(startHour == 0 || startHour < 10){
            time1 = ("0"+startHour );
        }
        if(endHour == 0|| endHour < 10){
            time2 = ("0"+endHour );
        }
        if(startMin == 0|| startMin < 10){
            min1 = ("0"+startMin);
        }
        if(endMin == 0|| endMin < 10){
            min2 = ("0"+endMin);
        }
        if(!sluttidkun) {
            fratid.setText(time1 + ":" + min1);
            tiltid.setText(time2 + ":" + min2);
        }
        else{
            tiltid.setText(time2 + ":" + min2);
        }
    }
}