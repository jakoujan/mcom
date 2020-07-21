/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial.action;

import com.mcss.mcom.dto.Read;
import java.math.BigDecimal;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 *
 * @author edgar
 */
public class TextFieldSerialReaderAction implements SerialReaderAction {

    private static final Logger LOGGER = Logger.getLogger(TextFieldSerialReaderAction.class);
    private final String pattern = "[^-?\\d.,]";

    private final JTextField textField;
    protected final Read read;
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public TextFieldSerialReaderAction(JTextField textField, Read read) {
        this.textField = textField;
        this.read = read;
    }

    @Override
    public void doTask(String as) {
        try {
            BigDecimal value;
            String r;
            if (as.contains(",")) {
                as = as.substring(3);
                r = as.replaceAll(pattern, "").trim();
                value = new BigDecimal(r).divide(ONE_HUNDRED);
            } else {
                r = as.replaceAll(pattern, "").trim();
                value = new BigDecimal(r);
            }
            this.textField.setText(r);
            this.read.setValue(value);
        } catch (Exception e) {
            LOGGER.error("Error en lectura", e);
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
