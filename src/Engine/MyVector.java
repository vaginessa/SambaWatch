/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Engine;

import java.util.Vector;

/**
 *
 * @author Akiel
 */
public class MyVector extends Vector{

    public MyVector(Object[] a) {
        super();
        add(a[0]);
        add(a[1]);
    }

    public void changeRute(String rute){
        set(0, rute);
    }

    public String getUrl(){
        return (String)get(0);
    }

    public void setActive(boolean active){
        set(1,active);
    }

    public boolean getActive(){
        return (Boolean)get(1);
    }
}
