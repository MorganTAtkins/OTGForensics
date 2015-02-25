package com.dissertation.otgforensics.myapplication;

/**
 * Created by Morgan on 24-Feb-15.
 */

import java.io.File;
import java.io.FilenameFilter;

public class Filter extends MainActivity {


    public File[] finder(String dirName, final String type) {
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {

                boolean t = filename.endsWith(type);
                return t;
            }
        });

    }


}
