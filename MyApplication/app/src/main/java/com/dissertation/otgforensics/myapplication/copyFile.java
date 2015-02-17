package com.dissertation.otgforensics.myapplication;


        import android.graphics.Path;
        import android.os.*;
        import android.widget.TextView;

        import java.io.BufferedOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.FilenameFilter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;




public class copyFile  implements Runnable {
@Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        FindMntDrive mnt = new FindMntDrive();
        String MntDir = mnt.DirPath;


        File source = new File("/storage/emulated/0/Download/");//declaration of the source dir

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


    }

    //using streams to copy files (http://www.journaldev.com/861/4-ways-to-copy-file-in-java)
    private static void copyFileUsingStream(File source, File dest, String sourceFilename) throws IOException {

        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        //InputStream is = null;
        //OutputStream os = null;
        //try {
        InputStream is = null;
        OutputStream out = null;

        System.out.println("file stream was opened ");
        String DestinationFile = (dest.toString() + "/" + sourceFilename);
        System.out.println("Destination file name " + DestinationFile);
        if (source.isDirectory())
        {
            if (!dest.exists() && !dest.mkdirs()) {
                throw new IOException("Cannot create dir " + dest.getAbsolutePath());
            }

            String[] children = source.list();
            for (int i=0; i<children.length; i++)
            {
                copyFileUsingStream(new File(source, children[i]),new File(dest, children[i]),sourceFilename);
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
                is = new FileInputStream(source);
                out = new BufferedOutputStream(new FileOutputStream(DestinationFile));
                System.out.println("");
                byte[] buffer = new byte[1024];
                int length;
                //for loop

                while ((length = is.read(buffer)) > 0)
                {
                    out.write(buffer, 0, length);
                    ////
                    // generate has for each file
                }

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
        is.close();
    }
}
