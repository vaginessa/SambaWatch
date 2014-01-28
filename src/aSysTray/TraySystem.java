/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aSysTray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 *
 * @author jaorozco
 */
public class TraySystem {

    private static TrayIcon trayIcon;
    private static SystemTray tray = null;
    private static JFrame owner;

    public static void message(String head, String message) {
        trayIcon.displayMessage(head, message, TrayIcon.MessageType.INFO);
    }

    private static TrayIcon getTrayIcon(Image i, String t, PopupMenu m) {
        if (trayIcon == null) {
            trayIcon = new TrayIcon(i, t, m);
        }
        return trayIcon;
    }

    public TraySystem(JFrame owner) {
        TraySystem.owner = owner;
    }

    public void active(Image a) {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();

//            URL resource = getClass().getResource(pathImage);
            Image image = a;//java.awt.Toolkit.getDefaultToolkit().getImage(resource);

//            Image image = java.awt.Toolkit.getDefaultToolkit().getImage(
//                    pathImage);


            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Exiting...");
                    System.exit(0);
                }
            };

            ActionListener restoreListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Restoring");
                    tray.remove(trayIcon);
                    owner.setVisible(true);
                    owner.setExtendedState(JFrame.NORMAL);
                }
            };


            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem1 = new MenuItem("Show SambaWatch");
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            defaultItem1.addActionListener(restoreListener);
            popup.add(defaultItem);
            popup.add(defaultItem1);
            try {
                getTrayIcon(image, "SambaWatch by Akiel", popup);
                trayIcon.setImageAutoSize(true);
                tray.add(trayIcon);
                owner.dispose();
//                trayIcon.displayMessage("AT v1.2",
//                        "Agenda 1.2 by $h@",
//                        TrayIcon.MessageType.INFO);

            } catch (AWTException e1) {
               // e1.printStackTrace();
            }
        }
    }
}
