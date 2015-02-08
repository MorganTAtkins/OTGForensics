package com.dissertation.otgforensics.myapplication;


        import android.graphics.Path;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.FilenameFilter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;


public class copyFile {

    public static void main() throws IOException {

        FindMntDrive mnt = new FindMntDrive();
        String MntDir = mnt.DirPath;



        File source = new File("/storage/emulated/0/Download/");
        //String sourcePath = ;

        //System.out.println(sourcePath);
        if (source.canRead()) {
            System.out.println("Can access the dir.");
            File sourceFiles[] = source.listFiles();
            for (int i = 0; i < sourceFiles.length; i++) {

                System.out.println("file path: " + sourceFiles[i].toString());
            }
        }
        else if (!source.canRead())
        {

            System.out.println("Can't access the dir.");
        }
        File dest = new File("/storage/emulated/0/dest/");

        //copy file conventional way using Stream
        long start = System.nanoTime();
        //copyFileUsingStream(source, dest);
        System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));


    }

//using streams to copy files (http://www.journaldev.com/861/4-ways-to-copy-file-in-java)
    private static void copyFileUsingStream(File source, File dest) throws IOException {
       //InputStream is = null;
        //OutputStream os = null;
        //try {
        InputStream is = new FileInputStream(source);
        OutputStream os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            //for loop
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);

            }
        //}
        //finally {
            is.close();
            os.close();
       // }
    }
}
