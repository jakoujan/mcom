/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial;

/**
 *
 * @author Edgar
 */
public class PortConfig {

    private String name;
    private Integer baudRate;
    private PortConfigItem parity;
    private Integer dataBits;
    private PortConfigItem stopBits;

    public PortConfig() {

    }

    public PortConfig(String name, Integer baudRate, PortConfigItem parity, Integer dataBits, PortConfigItem stopBits) {
        this.name = name;
        this.baudRate = baudRate;
        this.parity = parity;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public PortConfigItem getParity() {
        return parity;
    }

    public void setParity(PortConfigItem parity) {
        this.parity = parity;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public void setDataBits(Integer dataBits) {
        this.dataBits = dataBits;
    }

    public PortConfigItem getStopBits() {
        return stopBits;
    }

    public void setStopBits(PortConfigItem stopBits) {
        this.stopBits = stopBits;
    }
}
