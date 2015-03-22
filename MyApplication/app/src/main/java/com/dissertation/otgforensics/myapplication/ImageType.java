package com.dissertation.otgforensics.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Morgan on 19/03/2015.
 */
public class ImageType {

    InputStream is = null;
    OutputStream out = null;
    File destination = new File( "/sdcard/dest/");
    String type;
    public ListView AOL;




    public void main() {

        MainActivity MainActivity = new MainActivity();
        AOL = (ListView) MainActivity.findViewById(R.id.arrayOutputList);




                System.out.println("Destination ### = " +destination);
                //DialogBox("Enter file type ", "enter (.jpg/.txt/.mp3/etc)", "yes");
                //ImageType imgtyp = new ImageType();
                //imgtyp.dispayTypeSel();

                Filter filter = new Filter();
                //File[] JpgFiles = filter.finder(MntDir, type);

                System.out.println("filter finished ");
                ////////
                // create an array list with SFL as name
                ArrayList<String> SFL = new ArrayList<String>();
                //create the array adaptor for passing data to the list view
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.thisContext(), R.layout.layout);
                //create a list adaptor from the array adaptor (in place as a work around)
                ListAdapter LA = arrayAdapter;
                //create a file array from the contents of the source dir

                System.out.println(MainActivity.JpgFiles.toString());
                //for each file in the files array do the actions
                for (int i = 0; i < MainActivity.JpgFiles.length; i++) {//actions to be done on each of the files in the files array
                    //adding the file names to the SFL array
                    SFL.add(MainActivity.JpgFiles[i].toString());
                    //debugging
                    System.out.println("files " + +i + " " + MainActivity.JpgFiles[i].toString());
                    //adding each of the files to the array adaptor
                    arrayAdapter.add(MainActivity.JpgFiles[i].toString());
                }
                // popularing the list view with LA list array
                AOL.setAdapter(LA);
                //debugging

                imagetype(MainActivity.JpgFiles);



    }







    public void imagetype(File[] imgtyp) {
        File[] file = imgtyp;
        System.out.println(destination);
        for (int i = 0; i < file.length; i++) {
            try {


                System.out.println("Source: " + file[i]);
                System.out.println("name of file; "+file[i].getName());
                System.out.println("destination string; "+destination.toString());
                //pulling from source
                is = new FileInputStream(file[i]);
                //Pushing to DestinationFile
                out = new BufferedOutputStream(new FileOutputStream(destination.toString() +"/"+file[i].getName()));
                System.out.println("out = "+destination.toString() +"/"+file[i].getName());
                byte[] buffer = new byte[1024];
                int length;
                //for loop

                while ((length = is.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}

