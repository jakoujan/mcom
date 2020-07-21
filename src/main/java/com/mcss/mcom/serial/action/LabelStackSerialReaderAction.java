/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial.action;

import com.mcss.mcom.dto.Read;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author edgar
 */
public class LabelStackSerialReaderAction implements SerialReaderAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabelSerialReaderAction.class);

    private final DecimalFormat f = (DecimalFormat) NumberFormat.getInstance(Locale.US);
    private final DecimalFormatSymbols symbols = f.getDecimalFormatSymbols();

    protected final List<JLabel> labels;
    protected final Read read;

    public LabelStackSerialReaderAction(JLabel label, Read read) {
        this.labels = new ArrayList<>();
        this.labels.add(label);
        this.read = read;
        symbols.setGroupingSeparator(',');
        f.setDecimalFormatSymbols(symbols);
    }

    public LabelStackSerialReaderAction(Read read) {
        this.labels = new ArrayList<>();
        this.read = read;
        symbols.setGroupingSeparator(',');
        f.setDecimalFormatSymbols(symbols);
    }

    public Read getRead() {
        return read;
    }

    public void addLabel(JLabel label) {
        if (!labels.contains(label)) {
            this.labels.add(label);
        }
    }

    @Override
    public void doTask(String as) {
        String r = "";
        try {
            as = as.replace(";8", "");
            
            r = as.replaceAll(PATTERN, "").trim();
           
            this.read.setValue(new BigDecimal(r));
            // Para indicadores METTLER
            this.read.setValue(new BigDecimal(r).divide(new BigDecimal(1000000)));
            this.labels.forEach(label -> {
                label.setText(f.format(this.read.getValue() != null ? this.read.getValue() : BigDecimal.ZERO));
            });

        } catch (Exception e) {
            LOGGER.error("Error en la lectura: " + r);
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
