package com.dissertation.otgforensics.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Morgan on 24-Feb-15.
 */
public class alertBox extends MainActivity {

    public String Attention(String Title, String Message, String txtNeed) {
        // creates alertDialog box based on the main activity context
        AlertDialog.Builder alertBox1 = new AlertDialog.Builder(thisContext());
        String type= "1";
        // Setting Dialog Title
        alertBox1.setTitle(Title);


        // Setting Dialog Message
        alertBox1.setMessage(Message);
        // setting the text box for user input

        if (txtNeed == "false") {

        }
        else {
            final EditText input = new EditText(getApplicationContext());
            alertBox1.setView(input);
            input.setTextColor(Color.BLACK);
            // Setting OK Button
            alertBox1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    // debugging
                    System.out.println("user input = " + input.getText().toString());
                    String entered = input.getText().toString();
                    //take the user input and applies it to the customDir Variable
                    //MntDir = MntDir.toString()+input.getText().toString();
                    //declaration of the text view and linking it the Trgtdir variable
                    //TextView TrgtDir = (TextView)findViewById(R.id.TrgtDir);
                    //outputs the dir the text field

                    //TrgtDir.setText("Directory: " + MntDir);
                    // debugging
                    //System.out.println("customDir = " + MntDir);
                }
            });
            alertBox1.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //MntDir = " ";
                }
            });
        }

        // Showing Alert Message
        alertBox1.show(); return type;

   }

}
