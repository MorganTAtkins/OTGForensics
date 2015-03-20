package com.dissertation.otgforensics.myapplication;

/**
 * Created by Morgan on 27-Feb-15.
 */
public class checkSum {
    private boolean match;
    public boolean checkHash(String destHash, String sourceHash)
    {
        if (destHash == sourceHash)
        {
            match = true;

        }
        else
        {
            match = false;
        }


        return match;
    }
}
