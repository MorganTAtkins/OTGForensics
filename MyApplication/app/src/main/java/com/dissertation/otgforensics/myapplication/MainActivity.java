package com.dissertation.otgforensics.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;

import android.os.Bundle;

import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.TextView;

import java.io.File;

import java.util.ArrayList;


import android.view.View.OnClickListener;
import android.widget.Toast;

//#################  Fix the landscape to portrait Bug!! ####################\\
//#################  Fix the landscape to portrait Bug!! ####################\\


public class MainActivity extends Activity {

    HashGeneratorUtils HashGenUtils = new HashGeneratorUtils();
    HashGenerationException HashGenerationEx = new HashGenerationException();

    public Button imagetype;
    public Button imagingBtn;
    public Button dirContent ;
    public Button dirSelector ;
    public Button copyKill ;
    public Button reMnt;



    public volatile boolean running = true;

    public String customDir;
    public String MntDir ;
    public String tempMntDir = "/sdcard/usbStorage/";

    public String filePath = "/storage/emulated/0/dest/";
    public String type = ".docx";
    public ListView AOL;
    public Context context = this;

    /* Checks if external storage is the usb mnt point */
    public void isExternalStorage() {

        File pointer = new File(tempMntDir);
        if (pointer.exists()){
            MntDir = tempMntDir;
            Button ImgBtn = (Button) findViewById(R.id.ImagingBtn);
            ImgBtn.setBackgroundColor(Color.GREEN);

            System.out.println("External Storage Dir = " + MntDir);

            //declaration of the text view and linking it the Trgtdir variable
            TextView TrgtDir = (TextView) findViewById(R.id.TrgtDir);
            //outputs the dir the text field
            TrgtDir.setText("Source Directory: " + MntDir);

            imagingBtn.setEnabled(false);
            copyKill.setEnabled(false);
            //return true;
        } else {
            MntDir = Environment.getExternalStorageDirectory().toString();
            TextView TrgtDir = (TextView) findViewById(R.id.TrgtDir);
            TrgtDir.setText("Source Directory: " + MntDir);
            Dialog("Attention", "Enter source destination", "false");
            imagingBtn.setEnabled(false);
            copyKill.setEnabled(false);
            //return false;
        }
        //######
        // change icon or text label to indicate status
        //colour circle == green
        //colour circle if red by default
        //######
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        isExternalStorage();
        Dialog("Warning", "Lock device to portrait!", "false");

        //ListView AOL;
        //ListAdapter LA;
        AOL = (ListView) findViewById(R.id.arrayOutputList);
        //AOL.setOnItemClickListener()

        String state = Environment.getExternalStorageDirectory().toString();
            System.out.println(state);
    }

    public OnClickListener myClickListener = new OnClickListener() {
        public void onClick(View v) {
            //code to be written to handle the click event
        }
    };

    public void addListenerOnButton() {

        dirSelector = (Button) findViewById(R.id.dirSelector);
        dirSelector.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Call FC.Dialog()");
                Dialog("Please input source destination", "Enter usb mnt point", "true");
                imagingBtn.setEnabled(true);
                copyKill.setEnabled(true);
            }
        });


        //Select a specific button to link to an action
        imagingBtn  = (Button) findViewById(R.id.ImagingBtn);
        imagingBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("from = " + MntDir);
                System.out.println("to = " + filePath);
                // operations to be performed on a background thread

                copyFile CF = new copyFile();
                //CF.run();
                //copyFile will run as long as running == true

                    try {
                        System.out.println("let it begin "+ MntDir);
                        new copyFile().execute(MntDir);
                    } catch (Exception e) {
                        System.out.println("Exception");
                        running = false;
                    }


            }
        });

        copyKill    = (Button) findViewById(R.id.copyKillSwitch);
        copyKill.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });


        dirContent  = (Button) findViewById(R.id.dirContent);
        dirContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             /*ListView AOL;
             //ListAdapter LA;
             AOL = (ListView)findViewById(R.id.arrayOutputList);
             AOL.setOnItemClickListener(MainActivity.this);*/
                HashGeneratorUtils HashGenUtils = new HashGeneratorUtils();
                //create a new instance of the file ops class
                System.out.println("MntDir: " + MntDir);
                System.out.println("filePath: " + filePath);
                fileOps fops = new fileOps(MntDir, filePath);
                //create an array list with SFL as name
                ArrayList<String> SFL = new ArrayList<String>();
                //create the array adaptor for passing data to the list view
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.layout);
                //create a list adaptor from the array adaptor (in place as a work around)
                ListAdapter LA = arrayAdapter;
                //create a file array from the contents of the source dir
                File[] files = fops.getSourceFiles();
                //System.out.println(files.toString());
                //for each file in the files array do the actions
                for (int i = 0; i < files.length; i++) {//actions to be done on each of the files in the files array
                    //adding the file names to the SFL array
                    SFL.add(files[i].toString());
                    //debugging
                    System.out.println("files " + +i + " " + files[i].toString());
                    //adding each of the files to the array adaptor
                    //File hV = files[i];//hV stands for  hash value
                    arrayAdapter.add(files[i].toString());
                    File source = files[i];
                    String md5Hash;
                    try {
                         md5Hash = HashGenUtils.generateMD5(source);
                         arrayAdapter.add("   "+md5Hash);
                        }
                    catch (HashGenerationException ex)
                        {
                        ex.printStackTrace();
                        }




                }
                // popularing the list view with LA list array
                AOL.setAdapter(LA);
                //debugging
                System.out.println("break here #######");
            }
        });

        imagetype  = (Button) findViewById(R.id.imagetype);
        imagetype.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("start of FC");
                DialogBox("Enter file type ", "enter (.jpg/.txt/.mp3/etc)", "yes");


                Filter filter = new Filter();
                File[] JpgFiles = filter.finder(MntDir, type);
                //System.out.println(filter.finder(MntDir).toString());
                System.out.println("filter finished ");
                ////////
                // create an array list with SFL as name
                ArrayList<String> SFL = new ArrayList<String>();
                //create the array adaptor for passing data to the list view
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.layout);
                //create a list adaptor from the array adaptor (in place as a work around)
                ListAdapter LA = arrayAdapter;
                //create a file array from the contents of the source dir
                //File[] files = fops.getSourceFiles();
                System.out.println(JpgFiles.toString());
                //for each file in the files array do the actions
                for (int i = 0; i < JpgFiles.length; i++) {//actions to be done on each of the files in the files array
                    //adding the file names to the SFL array
                    SFL.add(JpgFiles[i].toString());
                    //debugging
                    System.out.println("files " + +i + " " + JpgFiles[i].toString());
                    //adding each of the files to the array adaptor
                    arrayAdapter.add(JpgFiles[i].toString());
                }
                // popularing the list view with LA list array
                AOL.setAdapter(LA);
                //debugging

            }

        });

        reMnt =   (Button) findViewById(R.id.reMnt);
        reMnt.setOnClickListener(new OnClickListener() {
           @Override
            public void onClick(View view) {
               isExternalStorage();

           }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //@Override
    public void md5button(View v) {
        TextView md5hashOutput;
        md5hashOutput = (TextView) findViewById(R.id.md5hashOutput);
        //MD5Digest();
        md5hashOutput.setText(md5hashOutput.getText());

    }

    public void ImagingBtn(View v) {


    }

    public void Dialog(String Title, String Message, String txtNeed) {
        // creates alertDialog box based on the main activity context
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle(Title);


        // Setting Dialog Message
        alertDialog.setMessage(Message);
        // setting the text box for user input

        if (txtNeed == "false") {

        } else {
            final EditText input = new EditText(getBaseContext());
            alertDialog.setView(input);
            input.setTextColor(Color.BLACK);
            // Setting OK Button
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    // debugging
                    System.out.println("user input = " + input.getText().toString());
                    //take the user input and applies it to the customDir Variable
                    MntDir = input.getText().toString();
                    //declaration of the text view and linking it the Trgtdir variable
                    TextView TrgtDir = (TextView) findViewById(R.id.TrgtDir);
                    //outputs the dir the text field

                    TrgtDir.setText("Directory: " + MntDir);
                    // debugging
                    System.out.println("customDir = " + MntDir);
                }
            });
            alertDialog.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MntDir = " ";
                }
            });
        }

        // Showing Alert Message
        alertDialog.show();

    }

    public void DialogBox(String Title, String Message, String txtNeed) {
        // creates alertDialog box based on the main activity context
        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertBox.setTitle(Title);


        // Setting Dialog Message
        alertBox.setMessage(Message);
        // setting the text box for user input

        if (txtNeed == "false") {

        } else {
            final EditText input = new EditText(getBaseContext());
            alertBox.setView(input);
            input.setTextColor(Color.BLACK);
            // Setting OK Button
            alertBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    // debugging
                    System.out.println("user input = " + input.getText().toString());
                    type = input.getText().toString();

                }
            });
            alertBox.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //MntDir = " ";
                }
            });
        }

        // Showing Alert Message
        alertBox.show();

    }
}