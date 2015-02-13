package com.dissertation.otgforensics.myapplication;

import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgan on 12-Feb-15.
 */
public class fileOps {

    public File source = null;
    public File dest = null;
    public File[] sourceFileList;

    //public String from = "/storage/emulated/0/Download/";//
    //public String  to = "/storage/emulated/0/dest/";//

    public fileOps(String from ,String to)
    {
        source = new File(from);//declaration of the source dir
        dest = new File(to);// constructor for the destination

    }
    public File[] getSourceFiles()
    {
        if (source == null || dest == null){
            System.out.println("source or destination is null ");
            return null;
        }
        sourceFileList = source.listFiles();
        return sourceFileList;
    }


}
