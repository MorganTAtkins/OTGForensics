package com.dissertation.otgforensics.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.logging.Handler;

/**
 * Created by Morgan on 31/03/2015.
 */
public class Image extends MainActivity{

    public void main(){

            System.out.println("from = "+MntDir);
            System.out.println("to = "+filePath);
            // operations to be performed on a background thread
             pBar = (ProgressBar)findViewById(R.id.progressBar);


            try
            {
                pBar.setVisibility(View.VISIBLE);
                System.out.println("let it begin " + MntDir);
                startTime = System.nanoTime();
                //###
                new copyFile().execute(MntDir);
                //###
                System.out.println("Time taken by Stream Copy = " + (System.nanoTime() - startTime));

                //running = false;

            }

            catch(Exception e)
            {
                System.out.println("Exception");
                //running = false;
            }
            pBar.setVisibility(View.INVISIBLE);


}
}




