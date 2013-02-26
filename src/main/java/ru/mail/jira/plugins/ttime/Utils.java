package ru.mail.jira.plugins.ttime;

import java.util.BitSet;

/**
 * This class contains static utility methods.
 * 
 * @author Andrey Markelov
 */
public class Utils
{
    /**
     * Get string from Bitset.
     */
    public static String bitSetToStr(BitSet bitSet)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < 7; i++)
        {
            if(bitSet.get(i))
            {
                sb.append("1");
            }
            else
            {
                sb.append("0");
            }
        }

        return sb.toString();
    }

    /**
     * Calculate time difference.
     */
    public static long caclDiffTimes(
        long lastTime,
        long oldVal,
        long currTime)
    {
        return (currTime - lastTime) + oldVal;
    }

    /**
     * Bitset from string.
     */
    public static BitSet getBitSetFromStr(String bitSetStr)
    {
        BitSet bitSet = new BitSet(7);
        for(int i = 0; i < bitSetStr.length(); i++)
        {
            if(bitSetStr.charAt(i) == '1')
            {
                bitSet.set(i);
            }
        }

        return bitSet;
    }

    /**
     * Get long from object.
     */
    public static Long getObjectAsLong(Object obj)
    {
        try
        {
            return Long.valueOf(obj.toString());
        }
        catch (Exception nex)
        {
            return 0L;
        }
    }

    /**
     * Check string to validity.
     */
    public static boolean isValid(String str)
    {
        return (str != null && str.length() > 0);
    }

    /**
     * Parse time to string.
     */
    public static String parseTime(Long time)
    {
        StringBuilder sb = new StringBuilder();

        if (time <= 0)
        {
            sb.append("0 m");
        }
        else
        {
            long secs = time/1000;
            long hours = secs/(60 * 60);
            if (hours > 0)
            {
                sb.append(hours).append(" h").append(" ");
                secs = secs - (hours * 60 * 60);
            }
            sb.append(secs/60).append(" m");
        }

        return sb.toString();
    }

    /**
     * Private constructor.
     */
    private Utils() {}
}
