/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.mcom.serial.action;

import com.mcss.mcom.dto.Read;
import javax.swing.JLabel;








public class OnDemandSerialReaderAction extends LabelSerialReaderAction
{
  private final OnDemandAction action;
  
  public OnDemandSerialReaderAction(JLabel label, Read read, OnDemandAction action)
  {
    super(label, read);
    this.action = action;
  }
  
  public void doTask(String as)
  {
    super.doTask(as);
    action.onDemandDo();
  }
}
