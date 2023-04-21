package service;

import javax.swing.JOptionPane;

public class Utils {

    private static boolean debug = true;

    public static void messageBox(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void messageBox(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean yesNoBox(String message, String title) {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static void errorBox(String message, String error) {
        JOptionPane.showMessageDialog(null, message, "Error: "+error, JOptionPane.ERROR_MESSAGE);
    }

    public static void successBox(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean isStrEqual(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

    public static void debugBox(String ...info)
    {
        if(!debug) return;
        String message = "";
        for(int i = 0; i < info.length; i++)
        {
            message += info[i] + ": ";
            message += info[++i] + "\n";
        }
        messageBox(message, "Debug");
    }

}
