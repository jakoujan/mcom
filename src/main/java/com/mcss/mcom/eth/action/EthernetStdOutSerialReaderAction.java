/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.eth.action;

import com.mcss.mcom.action.*;
import java.util.Arrays;

public class EthernetStdOutSerialReaderAction implements ReaderAction {

    @Override
    public void doTask(String as) {
        System.out.println(as);
    }

    @Override
    public void doTask(byte[] buffer) {
        System.out.println(Arrays.toString(buffer));
    }

    @Override
    public void reset() {
        
    }
}
