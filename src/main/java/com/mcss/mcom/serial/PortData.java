/*
 * To change PortData template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial;

import java.util.ArrayList;
import java.util.List;
import jssc.SerialPortList;

/**
 *
 * @author Edgar
 */
public final class PortData {

    private String[] ports = null;
    private final List<String> avalablePort = new ArrayList<>();
    private final List<Integer> baudRate = new ArrayList<>();
    private static final List<PortConfigItem> parity = new ArrayList<>();
    private final List<Integer> databits = new ArrayList<>();
    private static final List<PortConfigItem> stopbits = new ArrayList<>();

    /**
     *
     * @param value
     * @return
     */
    public static PortConfigItem findParity(Integer value) {
        if (value != null) {
            List<PortConfigItem> parities = getStaticParity();
            for (PortConfigItem p : parities) {
                if (p.getId() == value) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param value
     * @return
     */
    public static PortConfigItem findStopBits(Integer value) {
        if (value != null) {
            List<PortConfigItem> sbs = getStaticStopBits();

            for (PortConfigItem s : sbs) {
                if (s.getId() == value) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     *
     */
    public PortData() {
        baudRate.add(300);
        baudRate.add(600);
        baudRate.add(1200);
        baudRate.add(2400);
        baudRate.add(4800);
        baudRate.add(9600);
        baudRate.add(19200);
        baudRate.add(38400);
        databits.add(5);
        databits.add(6);
        databits.add(7);
        databits.add(8);
        searchForPorts();
    }

    static {
        parity.add(new PortConfigItem(0, "None"));
        parity.add(new PortConfigItem(1, "Odd"));
        parity.add(new PortConfigItem(2, "Even"));
        parity.add(new PortConfigItem(3, "Mark"));
        parity.add(new PortConfigItem(4, "Space"));
        stopbits.add(new PortConfigItem(1, "1"));
        stopbits.add(new PortConfigItem(2, "1.5"));
        stopbits.add(new PortConfigItem(3, "2"));
    }

    /**
     *
     */
    public void searchForPorts() {
        ports = SerialPortList.getPortNames();
        for (Integer ix = 0; ix < ports.length; ix++) {
            avalablePort.add(ports[ix]);
        }
    }

    /**
     *
     * @return
     */
    public List<String> getAvailablePorts() {
        return avalablePort;
    }

    /**
     *
     * @return
     */
    public List<Integer> getBaudRate() {
        return baudRate;
    }

    /**
     *
     * @return
     */
    public static List<PortConfigItem> getStaticParity() {
        return parity;
    }

    public List<PortConfigItem> getParity() {
        return parity;
    }

    /**
     *
     * @return
     */
    public List<Integer> getDataBits() {
        return databits;
    }

    /**
     *
     * @return
     */
    public static List<PortConfigItem> getStaticStopBits() {
        return stopbits;
    }

    public List<PortConfigItem> getStopBits() {
        return stopbits;
    }
}
