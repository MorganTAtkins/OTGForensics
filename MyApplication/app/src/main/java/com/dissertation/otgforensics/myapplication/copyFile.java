package com.dissertation.otgforensics.myapplication;


        import android.graphics.Path;
        import android.os.*;
        import android.widget.Switch;
        import android.widget.TextView;

        import java.io.BufferedOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.FilenameFilter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.URL;


public class copyFile extends AsyncTask<String,String,String>{


    protected String doInBackground(String... strings){
        Looper.prepare();
        //android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        //FindMntDrive mnt = new FindMntDrive();
        MainActivity mA = new MainActivity();

         //mA.MntDir = "/storage/emulated/0/Download/";
        //declaration of the source dir

        //System.out.println(MntDir);


        File source = new File(mA.MntDir);

        File dest = new File("/storage/emulated/0/dest/");// constructor for the destination


        //System.out.println(sourcePath);
        if (source.canRead())
        {
            System.out.println("Can access the dir.");
            File sourceFiles[] = source.listFiles();
            for (int i = 0; i < sourceFiles.length; i++) {
                String fileName = sourceFiles[i].getName();
                //System.out.println("file path: " + sourceFiles[i].toString());
                // new Thread(new Runnable() {
                try {
                    copyFileUsingStream(sourceFiles[i], dest, fileName);
                }catch (IOException e) {
                    System.out.println("it done goofed");
                    e.printStackTrace();
                }


                //}).start();
            }
        } else if (!source.canRead()) {

            System.out.println("Can't access the dir.");
        }


        //copy file conventional way using Stream
        long start = System.nanoTime();
        //copyFileUsingStream(source, dest);
        System.out.println("Time taken by Stream Copy = " + (System.nanoTime() - start));

        return source.listFiles().toString();
    }

    protected void onProgressUpdate(Integer... progress) {
       //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
        //showDialog("Downloaded " + result + " bytes");
    }






    //using streams to copy files (http://www.journaldev.com/861/4-ways-to-copy-file-in-java)
    public static void copyFileUsingStream(File source, File dest, String sourceFilename) throws IOException {

        //android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        //InputStream is = null;
        //OutputStream os = null;
        //try {
        InputStream is = null;
        OutputStream out = null;
        //String DestinationFile= "not correctly initialised";

        System.out.println("file stream was opened ");


        //destOption(dest,sourceFilename);
        String DestinationFile = (dest.toString() + "/" + sourceFilename);
        System.out.println("Destination file name " + DestinationFile);

        if (source.isDirectory())
        {
            if (!dest.exists() && !dest.mkdirs())
                {
                throw new IOException("Cannot create dir " + dest.getAbsolutePath());
                }

        String[] children = source.list();
        for (int i=0; i<children.length; i++)
            {
                System.out.println("recursive call "+ i);
                System.out.println(sourceFilename);
                //DestinationFile = (dest.toString());
                System.out.println(DestinationFile);
                sourceFilename = "";
                //dedt and source get switch to create the next dir
                copyFileUsingStream(new File(source, children[i]),new File(DestinationFile, children[i]),sourceFilename);
            }
        } else
        {
            // make sure the directory we plan to store the recording in exists
            File directory = dest.getParentFile();
            if (directory != null && !directory.exists() && !directory.mkdirs())
            {
                throw new IOException("Cannot create dir " + directory.getAbsolutePath());
            }
            try {


                System.out.println("Source: "+ source);
                //pulling from source
                is = new FileInputStream(source);
                //Pushing to DestinationFile
                out = new BufferedOutputStream(new FileOutputStream(DestinationFile));
                System.out.println("");
                byte[] buffer = new byte[1024];
                int length;
                //for loop

                while ((length = is.read(buffer)) > 0)
                {
                    out.write(buffer, 0, length);
                    ////
                    // generate hash for each file

                }
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                if (out != null)
                {
                    try {
                        String md5Hash = HashGeneratorUtils.generateMD5(source);
                        //md5hashOutput.setText("MD5 Hash: " + md5Hash);
                        System.out.println("md5 " + md5Hash);

                        //String sha1Hash = HashGeneratorUtils.generateSHA1(source);
                        //SHA1HashOutput.setText("SHA-1 Hash: " + sha1Hash);
                        //System.out.println("sha1 " + sha1Hash);

                        //String sha256Hash = HashGeneratorUtils.generateSHA256(source);
                        //SHA256HashOutput.setText("SHA-256 Hash: " + sha256Hash);
                        //System.out.println("sha256 " + sha256Hash);

                        //public String md5Hasha = md5Hash;
                    }
                    catch (HashGenerationException ex)
                    {
                        ex.printStackTrace();
                    }
                    out.close();
                }
            }
        }

    }

}
