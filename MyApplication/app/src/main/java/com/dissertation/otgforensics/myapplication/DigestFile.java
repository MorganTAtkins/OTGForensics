package com.dissertation.otgforensics.myapplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.lang.Object.*;
//import java.org.apache.commons.codec.binary.Hex;
import java.io.PrintStream;
/**
 * Created by Morgan on 27-Jan-15.
 */
public class DigestFile
{
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
        FindMntDrive mnt = new FindMntDrive();
        String MntDir = mnt.DirPath;
        String file = mnt.DirPath;
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(new FileInputStream(file), md, 2048);
    }
    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws NoSuchAlgorithmException, IOException
    {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(digest);
        return result;
    }


}
