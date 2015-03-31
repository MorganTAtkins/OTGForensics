package com.dissertation.otgforensics.myapplication;

/**
 * Created by Morgan on 24-Feb-15.
 */

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.File;
import java.io.FilenameFilter;

public class Filter extends MainActivity {


    public File[] finder(String dirName, final String type) {
        System.out.println("filter");


        File dir = new File(dirName);

        File[] dirNameItems = dir.listFiles();
        for (int i = 0; i < dirNameItems.length; i++)
        {
            System.out.println("dirNameItems[" + i + "] = " + dirNameItems[i]);
            //File dirholder = new File(dirNameItems[i]);
            if (dirNameItems[i].isDirectory())
            {
                String dirNameHolder = dirNameItems[i].getAbsolutePath();

                System.out.println("dirholder.getAbsolutePath = " + dirNameItems[i].getAbsolutePath());
                System.out.println("dirNameHolder = " + dirNameHolder);

                finder(dirNameHolder, type);

            }

        }
             return dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    //System.out.println("filter2");
                    boolean t = filename.endsWith(type);
                    return t;
                }

            });





    }
}
