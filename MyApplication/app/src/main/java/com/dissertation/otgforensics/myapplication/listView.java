/*package com.dissertation.otgforensics.myapplication;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class listView extends MainActivity {
    ListView FLV = (ListView) findViewById(R.id.fileListView);
    public void run() {

       copyFile CF = new copyFile();
       //make a array adaptor
        //populate array with copyfile.sourcefile[] array or use copyfile.sourcefile[]
        //pass it through the array adaptor and populate the list view in the main activity

        List<Array> sFiles = CF.;
        sFiles.add(sFiles);
        sFiles.add("bar");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_single_choice,sFiles);

        FLV.setAdapter(arrayAdapter);
    }
}*/