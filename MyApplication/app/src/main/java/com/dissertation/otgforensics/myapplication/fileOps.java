package com.dissertation.otgforensics.myapplication;

import android.content.Context;
import android.provider.MediaStore;

import java.io.File;


/**
 * Created by Morgan on 12-Feb-15.
 */
public class fileOps extends MainActivity {

    public File source = null;
    public File dest = null;
    public File[] sourceFileList;

    //public String from = "/storage/emulated/0/Download/";//
    //public String  to = "/storage/emulated/0/dest/";//

    public fileOps(String from, String to) {
        System.out.println("I GOT TO HERE 1");
        source = new File(from);//declaration of the source dir
        System.out.println("I GOT TO HERE 2");
        dest = new File(to);// constructor for the destination
        System.out.println("I GOT TO HERE 3");
        if (!source.exists())
        {
            System.out.println("I GOT TO HERE 4");
            alertBox attention = new alertBox();
            System.out.println("I GOT TO HERE 5");
            String entered = (attention.Attention("ATTENTION", "Source doesn't exist, enter new dir","true"));
            source = new File(entered);
            //source = null; //new File("/storage/emulated/0/Download/");
            System.out.println("Source file doesn't exist");
            //return;

            //alertBox attention = new alertBox();
           // attention.DialogBox("ATTENTION", "Source doesn't exist", "false");
        }
    }

    public File[] getSourceFiles() {

        if (source == null || dest == null) {
            System.out.println("source or destination is null ");

            alertBox attention = new alertBox();
            String Type =(attention.Attention("ATTENTION", "Source doesn't exist, enter new dir", "true"));
            source = new File(Type);

        }
        sourceFileList = source.listFiles();
        return sourceFileList;
    }


}
