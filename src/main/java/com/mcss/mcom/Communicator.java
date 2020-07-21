package com.mcss.mcom;

import com.mcss.mcom.action.ReaderAction;
import java.io.IOException;

public interface Communicator {

    public static final int SERIAL_TYPE = 1;
    public static final int ETHERNET_TYPE = 2;

    public void connect() throws Exception;

    public void disconnect() throws Exception;

    public void write(String message) throws Exception;

    public void write(byte[] stream) throws Exception;

    public ReaderAction getAction();

    public boolean isConnected();

}
