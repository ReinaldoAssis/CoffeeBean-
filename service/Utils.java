package service;

import javax.swing.JOptionPane;

public class Utils {

    public static void messageBox(String message) {
        JOptionPane.showMessageDialog(null, message);
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

}
