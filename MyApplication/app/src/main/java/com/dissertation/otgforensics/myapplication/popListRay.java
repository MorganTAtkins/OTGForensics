package com.dissertation.otgforensics.myapplication;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Morgan on 20/03/2015.
 */
public class popListRay {

    public void popList(File[] JpgFiles, ListView AOL,ArrayAdapter arrayAdapter){
    System.out.println("popList");
        MainActivity MainActivity = new MainActivity();

    ////////
    // create an array list with SFL as name
    ArrayList<String> SFL = new ArrayList<String>();
    //create the array adaptor for passing data to the list view
    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.thisContext(), R.layout.layout);
    //create a list adaptor from the array adaptor (in place as a work around)
    ListAdapter LA = arrayAdapter;
    //create a file array from the contents of the source dir

    //System.out.println(JpgFiles.toString());
    //for each file in the files array do the actions
    for (int i = 0; i < JpgFiles.length; i++) {//actions to be done on each of the files in the files array
        //adding the file names to the SFL array
        SFL.add(JpgFiles[i].toString());
        //debugging
        System.out.println("file " + +i + " " + JpgFiles[i].toString());
        //adding each of the files to the array adaptor
        arrayAdapter.add(JpgFiles[i].toString());
    }
    // popularing the list view with LA list array
    AOL.setAdapter(LA);
    //debugging
    //return JpgFiles;


}
}
