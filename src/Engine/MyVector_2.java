/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Engine;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Akiel
 */
public class MyVector extends Vector implements Serializable{

    public MyVector(String rute, String user,String domain, String pass, Boolean active) {
        super();
        add(rute);
        add(user);
        add(domain);
        add(pass);
        add(active);
    }

    public void changeRute(String rute){
        set(0, rute);
    }

    public String getUrl(){
        return (String)get(0);
    }

    public String getUser(){
        return (String)get(1);
    }

    public String getDomain(){
        return (String)get(2);
    }

    public String getPass(){
        return (String)get(3);
    }

    public void setActive(boolean active){
        set(4,active);
    }

    public boolean getActive(){
        return (Boolean)get(4);
    }
}
