/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import GUI.MainFrame;
//import aSysTray.TraySystem;
import jcifs.smb.SmbFile;

/**
 *
 * @author Akiel
 */
public class TChecker extends Thread {

    private MyVector vector;
    private String user = "", password = "";
    private boolean running = true;

    public TChecker(MyVector vector) {
        super();
        this.vector = vector;
    }

    public TChecker(MyVector vector, String user, String password) {
        //Not implemented        
        super();
        this.vector = vector;
        this.user = user;
        this.password = password;
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
                SmbFile f = new SmbFile(vector.getUrl());
                f.connect();
                if (f.exists() && (f.isDirectory() || f.isFile())) {
                    if (vector.getActive() == false) {
                        vector.setActive(true);
                    }
                } else {
                    if (vector.getActive() == true) {
                        vector.setActive(false);                        
                    }
                }
            } catch (Exception ex) {
                if (vector.getActive() == true) {
                    vector.setActive(false);                 
                }                
            }
            MainFrame.repaintTable();
            sleep();

        }
    }
}
