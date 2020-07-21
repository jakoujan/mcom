/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.action;

/**
 *
 * @author edgar
 */
public interface ReaderAction {

    public static final String PATTERN = "[^-?\\d.]";

    /**
     *
     * @param as
     */
    public void doTask(String as);

    /**
     *
     * @param buffer
     */
    public abstract void doTask(byte[] buffer);
    
    public abstract void reset();

}
