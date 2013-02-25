package ru.mail.jira.plugins.ttime;

/**
 * This class contains static utility methods.
 * 
 * @author Andrey Markelov
 */
public class Utils
{
    /**
     * Private constructor.
     */
    private Utils() {}

    /**
     * Check string to validity.
     */
    public static boolean isValid(String str)
    {
        return (str != null && str.length() > 0);
    }
}
