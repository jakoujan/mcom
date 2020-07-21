 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial;

import java.io.Serializable;

/**
 *
 * @author Edgar
 */
public class PortConfigItem implements Serializable{

    private int id;
    private String description;

    public PortConfigItem() {
    }

    public PortConfigItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return this.description;
    }
}
