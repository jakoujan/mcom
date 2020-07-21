/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial.reader;

import com.mcss.mcom.action.ReaderAction;
import com.mcss.mcom.serial.SerialCommunicator;
import com.mcss.mcom.serial.action.SerialReaderAction;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.log4j.Logger;

/**
 *
 * @author edgar
 */
public class SerialPortReader implements SerialPortEventListener {

    private final static Logger LOGGER = Logger.getLogger(SerialPortReader.class);

    protected final SerialCommunicator communicator;
    protected final StringBuilder stringBuilder;
    protected final ReaderAction action;

    /**
     *
     * @param communicator
     * @param action
     */
    public SerialPortReader(SerialCommunicator communicator, ReaderAction action) {
        this.communicator = communicator;
        this.stringBuilder = new StringBuilder();
        this.action = action;
    }

    /**
     *
     * @param communicator
     * @param action
     * @return
     */
    public static SerialPortReader getInstance(SerialCommunicator communicator, SerialReaderAction action) {

        return new SerialPortReader(communicator, action);
    }

    public ReaderAction getAction() {
        return action;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                byte buffer[] = communicator.getSerialPort().readBytes();
                if (buffer != null) {
                    this.action.doTask(buffer);
                }
            } catch (SerialPortException | NullPointerException ex) {
                LOGGER.error("Error on serial Event", ex);
            }
        }
    }
}
