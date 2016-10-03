package com.example.jiahua.regionbilagdesign.Logic;

import android.support.v4.app.Fragment;

import com.example.jiahua.regionbilagdesign.Fragmenter.Laegeunderskrift_fragment;
import com.example.jiahua.regionbilagdesign.Fragmenter.Tolkbilag1_fragment;
import com.example.jiahua.regionbilagdesign.Fragmenter.Tolkbilag2_fragment;
import com.example.jiahua.regionbilagdesign.Fragmenter.Tolkbilag3_fragment;
import com.example.jiahua.regionbilagdesign.Fragmenter.Tolkensunderskrift_fragment;
import com.example.jiahua.regionbilagdesign.Fragmenter.Velkommen_fragment;

/**
 * Created by Jiahua on 28-09-2016.
 */

public class Fragmentmanager {

    public static Fragment Velkommenfragment = new Velkommen_fragment();
    public static Fragment Tolkbilag1fragment = new Tolkbilag1_fragment();
    public static Fragment Tolkbilag2fragment = new Tolkbilag2_fragment();
    public static Fragment Tolkbilag3fragment = new Tolkbilag3_fragment();
    public static Fragment Laegeunderskriftfragment = new Laegeunderskrift_fragment();
    public static Fragment Tolkensunderskriftfragment = new Tolkensunderskrift_fragment();

    public Fragment getVelkommenfragment(){

        return Velkommenfragment;
    }

    public Fragment getTolkbilag1fragment(){

        return Tolkbilag1fragment;
    }

    public Fragment getTolkbilag2fragment(){

        return Tolkbilag2fragment;
    }

    public Fragment getTolkbilag3fragment(){

        return Tolkbilag3fragment;
    }

    public static Fragment getLaegeunderskriftfragment() {

        return Laegeunderskriftfragment;
    }

    public static Fragment getTolkensunderskriftfragment() {

        return Tolkensunderskriftfragment;
    }

}
