/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import GUI.MainFrame;
//import aSysTray.TraySystem;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

/**
 *
 * @author Akiel
 */
public class TChecker extends Thread {

    private MyVector vector;
    private boolean running = true;

    public TChecker(MyVector vector) {
        super();
        this.vector = vector;
    }

    private void sleep() {
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            //
        }
    }

    public final void stopit() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(vector.getDomain(), vector.getUser(), vector.getPass());
                SmbFile f = new SmbFile(vector.getUrl(),auth);
                
                f.connect();
                if (f.exists() && (f.isDirectory() || f.isFile())) {
                    if (vector.getActive() == false) {
                        vector.setActive(true);
                        //TraySystem.message("State Changed to Active", vector.getUrl());
                    }
                } else {
                    if (vector.getActive() == true) {
                        vector.setActive(false);
                        //TraySystem.message("State Changed to Inactive", vector.getUrl());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                if (vector.getActive() == true) {
                    vector.setActive(false);
                   // TraySystem.message("State Changed to Inactive", vector.getUrl());
                }
                //Logger.getLogger(WatchMan.class.getName()).log(Level.SEVERE, null, ex);
            }
            MainFrame.repaintTable();
            sleep();

        }
    }
}
