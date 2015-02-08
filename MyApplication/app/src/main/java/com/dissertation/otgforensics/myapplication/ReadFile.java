package com.dissertation.otgforensics.myapplication;

import android.app.ExpandableListActivity;
import android.graphics.Path;
import android.provider.MediaStore;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.os.Bundle;
import android.app.Activity;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Morgan on 27-Jan-15.
 */
public class ReadFile
{
    FindMntDrive mnt = new FindMntDrive();
    //File MntDir = mnt.DirPath;

    public String path;
    //Path file = MntDir;
    private void main(String[] args) {

        File file ;
        String[] paths;
        try{
            // create new file object
            file = new File(mnt.file_path);

            // array of files and directory
            paths = file.list();

            // for each name in the path array
            for(String path:paths)
            {
                // prints filename and directory name
                System.out.println(path);
                //String pathCon = paths;
            }
        }catch(Exception e)
        {
            // if any error occurs
            e.printStackTrace();
        }
    }
    //public String qwerty = pathCon;
}