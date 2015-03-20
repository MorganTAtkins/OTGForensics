package com.dissertation.otgforensics.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;

import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;


/**
 * Created by Morgan on 17-Feb-15.
 */
public class fileChooser  {


    public void Dialog(Context context) {
        System.out.println("fileChooser");
        final MainActivity MainActivity = new MainActivity();

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        //Dialog dialog = new Dialog(this);
        // Setting Dialog Title
        alertDialog.setTitle("Enter a file type");
        //dialog.setTitle("Select source Dir");
        // Setting Dialog Message
        alertDialog.setMessage("Fire zee missiles");

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity MainA = new MainActivity();
                // Write your code here to execute after dialog closed
                Toast.makeText(MainA.thisContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }
}