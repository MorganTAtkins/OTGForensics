package com.dissertation.otgforensics.myapplication;


        import android.graphics.Path;

        import java.io.BufferedOutputStream;
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
        File dest = new File("/storage/emulated/0/dest/");
        //String sourcePath = ;

        //System.out.println(sourcePath);
        if (source.canRead()) {
            System.out.println("Can access the dir.");
            File sourceFiles[] = source.listFiles();
            for (int i = 0; i < sourceFiles.length; i++) {
                String fileName = sourceFiles[i].getName();
                System.out.println("file path: " + sourceFiles[i].toString());
                copyFileUsingStream(sourceFiles[i], dest,fileName);
            }
        }
        else if (!source.canRead())
        {

            System.out.println("Can't access the dir.");
        }


        //copy file conventional way using Stream
        long start = System.nanoTime();
        //copyFileUsingStream(source, dest);
        System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));


    }

//using streams to copy files (http://www.journaldev.com/861/4-ways-to-copy-file-in-java)
    private static void copyFileUsingStream(File source, File dest,String sourceFilename) throws IOException {
       //InputStream is = null;
        //OutputStream os = null;
        //try {
        InputStream is = new FileInputStream(source);
        System.out.println("file stream was opened ");
        String DestinationFile = (dest.toString()+"/"+sourceFilename);
        System.out.println("Destination file name " + DestinationFile );
        OutputStream out = null;

        try {
            out = new BufferedOutputStream(new FileOutputStream(DestinationFile));
            System.out.println("");
            byte[] buffer = new byte[1024];
            int length;
            //for loop
            while ((length = is.read(buffer)) > 0) {
                out.write(buffer, 0, length);

            }
        }finally {
            if (out !=null){
                out.close();
            }

        }

       /* OutputStream os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            //for loop
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);

            }
        //}
        //finally {

            os.close();
       // }*/
        is.close();
    }
}
