/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial;

import com.mcss.mcom.Communicator;
import com.mcss.mcom.action.ReaderAction;
import com.mcss.mcom.exception.EventListenerNotSetException;
import com.mcss.mcom.serial.reader.BufferedSerialPortReader;
import com.mcss.mcom.serial.reader.SerialPortReader;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialCommunicator implements Communicator {

    public final static int SPACE = 32;
    public final static int DASH = 45;
    public final static byte LINE_FEED = 10;
    public final static byte CARRIAGE_RETURN = 13;

    private SerialPort serialPort;
    private boolean connected = false;
    private PortConfig portConfig;
    private SerialPortReader serialPortReader;

    /**
     *
     */
    public SerialCommunicator() {
    }

    /**
     *
     * @param portConfig
     */
    public SerialCommunicator(PortConfig portConfig, ReaderAction action) {
        this.portConfig = portConfig;
        this.serialPortReader = new BufferedSerialPortReader(this, CARRIAGE_RETURN, action);
    }

    /**
     *
     * @return
     */
    public PortConfig getPortConfig() {
        return portConfig;
    }

    /**
     *
     * @param portConfig
     */
    public void setPortConfig(PortConfig portConfig) {
        this.portConfig = portConfig;
    }

    /**
     *
     * @return
     */
    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     *
     * @return
     */
    public SerialPortReader getSerialPortReader() {
        return serialPortReader;
    }

    /**
     *
     * @param serialPortReader
     */
    public void setSerialPortReader(SerialPortReader serialPortReader) {
        this.serialPortReader = serialPortReader;
    }

    /**
     * Connect to the selected port in the combo box pre: ports are already
     * found by using the searchForPorts method post: the connected comm port is
     * stored in commPort, otherwise, an exception is generated
     *
     * @throws SerialPortException
     * @throws com.mcss.mcom.exception.EventListenerNotSetException
     */
    @Override
    public void connect() throws SerialPortException, EventListenerNotSetException {
        if (this.serialPortReader == null) {
            throw new EventListenerNotSetException("Serial event listener not implemented");
        }
        if (this.portConfig == null) {
            throw new SerialPortException("", "connect", "Configuración no inicializada");
        }
        this.serialPort = new SerialPort(this.portConfig.getName());
        this.serialPort.openPort();
        this.serialPort.setParams(this.portConfig.getBaudRate(), this.portConfig.getDataBits(), this.portConfig.getStopBits().getId(), this.portConfig.getParity().getId());
        this.serialPort.addEventListener(this.serialPortReader);
        this.connected = true;
    }

    /**
     *
     * @throws SerialPortException
     */
    @Override
    public void disconnect() throws SerialPortException {
        this.serialPort.closePort();
        this.connected = false;
    }

    /**
     * If serial port is connected
     *
     * @return Boolean
     */
    @Override
    public boolean isConnected() {
        return connected;
    }

    /**
     *
     * @param data
     * @param sendCRLF
     * @throws SerialPortException
     */
    public void writeData(String data, boolean sendCRLF) throws SerialPortException {
        this.serialPort.writeString(data);
        if (sendCRLF) {
            this.serialPort.writeByte(CARRIAGE_RETURN);
            this.serialPort.writeByte(LINE_FEED);
        }
    }

    /**
     *
     * @param data
     * @throws SerialPortException
     */
    public void writeData(String data) throws SerialPortException {
        this.writeData(data, true);
    }

    /**
     *
     * @param data
     * @throws SerialPortException
     */
    public void writeData(char data) throws SerialPortException {
        this.serialPort.writeByte((byte) data);
    }

    /**
     *
     * @param data
     * @throws SerialPortException
     */
    public void writeData(byte data) throws SerialPortException {
        this.serialPort.writeByte(data);
    }

    /**
     *
     * @param bytes
     * @throws SerialPortException
     */
    public void writeData(byte[] bytes) throws SerialPortException {
        this.serialPort.writeBytes(bytes);
    }

    @Override
    public void write(String message) throws SerialPortException {
        this.writeData(message);
    }

    @Override
    public void write(byte[] stream) throws SerialPortException {
        this.writeData(stream);
    }

    @Override
    public ReaderAction getAction() {
        return this.serialPortReader.getAction();
    }

}
