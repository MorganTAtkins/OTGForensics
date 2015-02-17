package com.dissertation.otgforensics.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;

import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;


/**
 * Created by Morgan on 17-Feb-15.
 */
public class fileChooser extends MainActivity {
     public void Dialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        //Dialog dialog = new Dialog(this);
        // Setting Dialog Title
        alertDialog.setTitle("Select source Dir");
        //dialog.setTitle("Select source Dir");
        // Setting Dialog Message
        alertDialog.setMessage("Fire zee missiles");

        // setting the text box
        final EditText input = new EditText(this);
        alertDialog.setView(input);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });


        // Showing Alert Message
         alertDialog.show();

    }
}