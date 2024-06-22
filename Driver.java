import java.awt.*;

/**
 * The Driver class serves as the entry point for the application.
 * It initializes and displays the admin control panel.
 */
public class Driver
{
    public static void main(String[] args)
    {
        adminControlPanel admin = adminControlPanel.getInstance();

        EventQueue.invokeLater(() -> admin.setVisible(true));
    }
}