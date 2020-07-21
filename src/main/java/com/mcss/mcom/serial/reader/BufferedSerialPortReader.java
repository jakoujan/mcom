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
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Edgar
 */
public class BufferedSerialPortReader extends SerialPortReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(BufferedSerialPortReader.class);

    private final byte finalizer;

    public static BufferedSerialPortReader getInstance(SerialCommunicator communicator, byte finalizer, ReaderAction action) {
        return new BufferedSerialPortReader(communicator, finalizer, action);
    }

    public BufferedSerialPortReader(SerialCommunicator communicator, byte finalizer, ReaderAction action) {
        super(communicator, action);
        this.finalizer = finalizer;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            try {
                byte buffer[] = communicator.getSerialPort().readBytes();
                if (buffer != null) {
                    for (byte b : buffer) {
                        if (b != finalizer) {
                            stringBuilder.append((char) b);
                            LOGGER.debug(stringBuilder.toString());
                        } else {
                            action.doTask(this.stringBuilder.toString());
                            stringBuilder.setLength(0);
                        }
                    }
                }
            } catch (SerialPortException | NullPointerException ex) {
                LOGGER.error("Error on serial event on port " + communicator.getPortConfig().getName(), ex);
            }
        }
    }

}
