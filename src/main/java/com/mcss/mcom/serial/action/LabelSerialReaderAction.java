/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial.action;

import com.mcss.mcom.dto.Read;
import java.math.BigDecimal;
import javax.swing.JLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author edgar
 */
public class LabelSerialReaderAction implements SerialReaderAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabelSerialReaderAction.class);

    protected final JLabel label;
    protected final Read read;

    public LabelSerialReaderAction(JLabel label, Read read) {
        this.label = label;
        this.read = read;
    }

    @Override
    public void doTask(String as) {
        try {
            String pattern = "[^-?\\d.,]";
            String r = as.replaceAll(pattern, "").trim();
            this.label.setText(r);
            this.read.setValue(new BigDecimal(r));
        } catch (Exception e) {
        }
    }

    @Override
    public void doTask(byte[] buffer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
