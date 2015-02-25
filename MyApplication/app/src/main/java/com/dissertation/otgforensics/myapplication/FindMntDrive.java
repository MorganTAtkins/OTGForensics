package com.dissertation.otgforensics.myapplication;

import android.os.Environment;
import android.provider.Settings;

import java.io.File;

/**
 * Created by Morgan on 26-Jan-15.
 */

public class FindMntDrive {

    //look for mounted drive
    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath();
    //file_path = Environment.getExternalStorageDirectory().getAbsolutePath();
    // creates the file object
    File dir = new File(file_path);
    //String DirPath = new String(file_path);
    public String DirPath = file_path;
    //public File FDirPath = file_path;
}
