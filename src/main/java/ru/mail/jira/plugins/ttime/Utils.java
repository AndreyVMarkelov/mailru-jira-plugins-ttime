package ru.mail.jira.plugins.ttime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

/**
 * This class contains static utility methods.
 * 
 * @author Andrey Markelov
 */
public class Utils
{
    public static void main(String[] args)
    throws ParseException
    {
        Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-26 15:56:00");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-27 12:40:00");

        Date d3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-26 15:56:00");
        Date d4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-28 12:40:00");

        Date d5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-26 15:56:00");
        Date d6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-03-01 12:40:00");

        Date d7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-26 09:56:00");
        Date d8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-27 12:40:00");

        Date d9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-26 09:56:00");
        Date d10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-02-27 19:40:00");

        DataStruct dd = new DataStruct();
        dd.setStartDayTime(10);
        dd.setEndDayTime(19);
        dd.setMonday(true);
        dd.setTuesday(true);
        dd.setWednesday(false);
        dd.setThursday(true);
        dd.setFriday(true);

        System.out.println(parseTime(caclDiffTimes(d.getTime(), 0, d2.getTime(), dd)));
        System.out.println(parseTime(caclDiffTimes(d3.getTime(), 0, d4.getTime(), dd)));
        System.out.println(parseTime(caclDiffTimes(d5.getTime(), 0, d6.getTime(), dd)));
        System.out.println(parseTime(caclDiffTimes(d7.getTime(), 0, d8.getTime(), dd)));
        System.out.println(parseTime(caclDiffTimes(d9.getTime(), 0, d10.getTime(), dd)));
    }

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
        long currTime,
        DataStruct dataStruct)
    {
        Calendar calCurr = Calendar.getInstance();
        calCurr.setTimeInMillis(currTime);
        Calendar calLast = Calendar.getInstance();
        calLast.setTimeInMillis(lastTime);

        Calendar calCurrRealStart = Calendar.getInstance();
        calCurrRealStart.setTimeInMillis(lastTime);
        calCurrRealStart.set(Calendar.HOUR_OF_DAY, dataStruct.getStartDayTime());
        calCurrRealStart.set(Calendar.MINUTE, 0);
        calCurrRealStart.set(Calendar.SECOND, 0);

        long caclTime = 0;
        while (calCurrRealStart.before(calCurr))
        {
            if (!dataStruct.isWorkDay(calCurrRealStart.get(Calendar.DAY_OF_WEEK)))
            {
                calCurrRealStart.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }

            Calendar calDiffEnd = Calendar.getInstance();
            calDiffEnd.setTimeInMillis(calCurrRealStart.getTimeInMillis());
            calDiffEnd.set(Calendar.HOUR_OF_DAY, dataStruct.getEndDayTime());
            calDiffEnd.set(Calendar.MINUTE, 0);
            calDiffEnd.set(Calendar.SECOND, 0);

            if (calCurr.after(calDiffEnd))
            {
                if (calLast.after(calCurrRealStart))
                {
                    caclTime += calDiffEnd.getTimeInMillis() - calLast.getTimeInMillis();
                }
                else
                {
                    caclTime += calDiffEnd.getTimeInMillis() - calCurrRealStart.getTimeInMillis();
                }
            }
            else
            {
                if (calLast.after(calCurrRealStart))
                {
                    caclTime += calCurr.getTimeInMillis() - calLast.getTimeInMillis();
                }
                else
                {
                    caclTime += calCurr.getTimeInMillis() - calCurrRealStart.getTimeInMillis();
                }
            }

            calCurrRealStart.add(Calendar.DAY_OF_MONTH, 1);
        }

        return caclTime + oldVal;
    }

    /**
     * Bitset from string.
     */
    public static BitSet getBitSetFromStr(String bitSetStr)
    {
        BitSet bitSet = new BitSet(7);
        if (bitSetStr != null)
        {
            for(int i = 0; i < bitSetStr.length(); i++)
            {
                if(bitSetStr.charAt(i) == '1')
                {
                    bitSet.set(i);
                }
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
