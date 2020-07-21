package com.mcss.mcom.eth;

import com.mcss.mcom.Communicator;
import com.mcss.mcom.eth.action.EthernetStdOutSerialReaderAction;
import java.io.IOException;

public class Tester {

    public static void main(String args[]) throws IOException, InterruptedException, Exception {
        Communicator communicator = new EthernetCommunicator("192.168.1.86", 1200, new EthernetStdOutSerialReaderAction() , 1000);
        communicator.connect();
        Thread.sleep(5000);
        communicator.disconnect();
    }
}
