package com.dissertation.otgforensics.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBarActivity;

import java.io.File;

/**
 * Created by Morgan on 31-Jan-15.
 */
public class ReadFromFile extends MainActivity {

    /*Checks if external storage is available to at least read
   public boolean isExternalStorageReadable() {
       String state = Environment.getExternalStorageState();
       if (Environment.MEDIA_MOUNTED.equals(state) ||
               Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

          // Button ImgBtn = (Button) findViewById(R.id.ImagingBtn);
           //ImgBtn.setBackgroundColor(Color.GREEN);
           return true;
           }
           //######
           // change icon or text label to indicate status
           //colour circle == green
               //colour circle if red by default
           //######



       return false;

   }*/
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("LOG_TAG", "Directory not created");
        }
        return file;
    }


}
