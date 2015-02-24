package com.dissertation.otgforensics.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.content.Intent;
import android.net.Uri;

import android.view.View.OnClickListener;
import android.widget.Toast;

//#################  Fix the landscape to portrait Bug!! ####################\\
//#################  Fix the landscape to portrait Bug!! ####################\\


public class MainActivity extends Activity {

HashGeneratorUtils myHashGeneratorUtils = new HashGeneratorUtils();
    Context foo;



private Button hashButton;
private Button imagingBtn;
private Button dirContent;
private Button dirSelector;
private Button copyKill;
private volatile boolean running = true;

public  String customDir;
public  String MntDir = "/sdcard/usbStorage/";
public  String filePath = "/storage/emulated/0/dest/";
public  String type;

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

            Button ImgBtn = (Button) findViewById(R.id.ImagingBtn);
            ImgBtn.setBackgroundColor(Color.GREEN);
            //MntDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            System.out.println("External Storage Dir = "+MntDir);

            //declaration of the text view and linking it the Trgtdir variable
            TextView TrgtDir = (TextView)findViewById(R.id.TrgtDir);
            //outputs the dir the text field
            TrgtDir.setText("Source Directory: " + MntDir);

            imagingBtn.setEnabled(false);
            copyKill.setEnabled(false);
            return true;
        }
        else {
            Dialog("Attention","Please input source destination","false");
            return false;
        }
        //######
        // change icon or text label to indicate status
        //colour circle == green
        //colour circle if red by default
        //######
        }

    @Override
 public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            addListenerOnButton();
            isExternalStorageReadable();
            Dialog("Warning", "Lock device to portrait!", "false");


}

 public void addListenerOnButton() {

     dirSelector = (Button) findViewById(R.id.dirSelector);

     dirSelector.setOnClickListener(new OnClickListener()
     {
         @Override
         public void onClick(View view)
         {
             System.out.println("Call FC.Dialog()");
             Dialog("Please input source destination",MntDir.toString(),"true");
             imagingBtn.setEnabled(true);
             copyKill.setEnabled(true);
         }
     });



       //Select a specific button to link to an action
     imagingBtn = (Button) findViewById(R.id.ImagingBtn);

     imagingBtn.setOnClickListener(new OnClickListener()
     {
         @Override
         public void onClick(View view)
         {
             System.out.println("from = " + MntDir);
             System.out.println("to = " + filePath);
             // operations to be performed on a background thread

             copyFile CF = new copyFile();
             //CF.run();
             //copyFile will run as long as running == true
             while (running) {
                 try {
                     new copyFile().execute();
                 } catch (Exception e) {
                     System.out.println("Exception");
                     running = false;
                 }
             }

         }
     });
     copyKill = (Button) findViewById(R.id.copyKillSwitch);
     copyKill.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
             running = false;
         }
     });




     dirContent = (Button) findViewById(R.id.dirContent);
     dirContent.setOnClickListener(new OnClickListener(){
         @Override
         public void onClick(View view)
         {
             ListView AOL;
             //ListAdapter LA;
             AOL = (ListView)findViewById(R.id.arrayOutputList);

             //create a new instance of the file ops class
             System.out.println("MntDir: "+ MntDir);
             System.out.println("filePath: "+filePath);
             fileOps fops = new fileOps(MntDir,filePath);
             //create an array list with SFL as name
             ArrayList<String> SFL = new ArrayList<String>();
             //create the array adaptor for passing data to the list view
             ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),R.layout.layout);
             //create a list adaptor from the array adaptor (in place as a work around)
             ListAdapter LA = arrayAdapter;
             //create a file array from the contents of the source dir
             File[] files = fops.getSourceFiles();
             //System.out.println(files.toString());
             //for each file in the files array do the actions
             for( int i = 0; i<files.length; i++)
             {//actions to be done on each of the files in the files array
                 //adding the file names to the SFL array
                 SFL.add(files[i].toString());
                 //debugging
                 System.out.println("files " + +i + " " + files[i].toString());
                 //adding each of the files to the array adaptor
                 arrayAdapter.add(files[i].toString());
             }
             // popularing the list view with LA list array
             AOL.setAdapter(LA);
             //debugging
             System.out.println("break here #######");
         }
     });




     hashButton = (Button) findViewById(R.id.md5button);
     hashButton.setOnClickListener(new OnClickListener()
     {
        @Override
        public void onClick(View view)
        {
           fileChooser FC = new fileChooser();
           //FC.loadFileList();
           System.out.println("start of FC");

           Filter filter = new Filter();
           DialogBox("Enter file type ","enter (.jpg/.txt/.mp3/etc","yes");

           File[] JpgFiles = filter.finder(MntDir,type);
           //System.out.println(filter.finder(MntDir).toString());
           System.out.println("filter finished ");
           //////
            ListView AOL;
            //ListAdapter LA;
            AOL = (ListView)findViewById(R.id.arrayOutputList);

            //create a new instance of the file ops class
            //System.out.println("MntDir: "+ MntDir);
            //System.out.println("filePath: "+filePath);
            //fileOps fops = new fileOps(MntDir,filePath);
            //create an array list with SFL as name
            ArrayList<String> SFL = new ArrayList<String>();
            //create the array adaptor for passing data to the list view
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(),R.layout.layout);
            //create a list adaptor from the array adaptor (in place as a work around)
            ListAdapter LA = arrayAdapter;
            //create a file array from the contents of the source dir
            //File[] files = fops.getSourceFiles();
            System.out.println(JpgFiles.toString());
            //for each file in the files array do the actions
            for( int i = 0; i<JpgFiles.length; i++)
            {//actions to be done on each of the files in the files array
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

 }


        /*public void HashGeneratorUtils() {

        }

        public static String generateMD5(String message) throws HashGenerationException {
            return hashString(message, "MD5");
        }

        public static String generateSHA1(String message) throws HashGenerationException {
            return hashString(message, "SHA-1");
        }

        public static String generateSHA256(String message) throws HashGenerationException {
            return hashString(message, "SHA-256");
        }

        public static String generateMD5(File file) throws HashGenerationException {
            return hashFile(file, "MD5");
        }

        public static String generateSHA1(File file) throws HashGenerationException {
            return hashFile(file, "SHA-1");
        }

        public static String generateSHA256(File file) throws HashGenerationException {
            return hashFile(file, "SHA-256");
        }

        private static String hashString(String message, String algorithm)
                throws HashGenerationException {

            try {
                MessageDigest digest = MessageDigest.getInstance(algorithm);
                byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

                return convertByteArrayToHexString(hashedBytes);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                throw new HashGenerationException(
                        "Could not generate hash from String", ex);
            }
        }

        private static String hashFile(File file, String algorithm)
                throws HashGenerationException {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                MessageDigest digest = MessageDigest.getInstance(algorithm);

                byte[] bytesBuffer = new byte[1024];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                    digest.update(bytesBuffer, 0, bytesRead);
                }

                byte[] hashedBytes = digest.digest();

                return convertByteArrayToHexString(hashedBytes);
            } catch (NoSuchAlgorithmException | IOException ex) {
                throw new HashGenerationException(
                        "Could not generate hash from file", ex);
            }
        }

        private static String convertByteArrayToHexString(byte[] arrayBytes) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < arrayBytes.length; i++) {
                stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return stringBuffer.toString();
        }

*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //@Override
 /** public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
  */
    //@Override
    public void md5button(View v)
    {
        TextView md5hashOutput;
        md5hashOutput = (TextView)findViewById(R.id.md5hashOutput);
        //MD5Digest();
        md5hashOutput.setText(md5hashOutput.getText());

    }
    public void ImagingBtn(View v)
    {


    }

public void Dialog (String Title,String Message,String txtNeed){
    // creates alertDialog box based on the main activity context
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

    // Setting Dialog Title
    alertDialog.setTitle(Title);


    // Setting Dialog Message
    alertDialog.setMessage(Message);
    // setting the text box for user input

    if (txtNeed == "false"){

    }else
    {
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
                MntDir = MntDir.toString()+input.getText().toString();
                //declaration of the text view and linking it the Trgtdir variable
                TextView TrgtDir = (TextView)findViewById(R.id.TrgtDir);
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
    public void DialogBox (String Title,String Message,String txtNeed){
        // creates alertDialog box based on the main activity context
        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);

        // Setting Dialog Title
        alertBox.setTitle(Title);


        // Setting Dialog Message
        alertBox.setMessage(Message);
        // setting the text box for user input

        if (txtNeed == "false"){

        }else
        {
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