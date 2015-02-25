package com.dissertation.otgforensics.myapplication;

import android.graphics.Color;
import android.widget.Button;

/**
 * Created by Morgan on 24-Feb-15.
 */
public class threadBlink extends MainActivity {

    public void blink() {
        Button ImgBtn = (Button) findViewById(R.id.ImagingBtn);
        ImgBtn.setBackgroundColor(Color.GREEN);
    }
}
