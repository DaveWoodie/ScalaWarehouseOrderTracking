/**
 * @author dwoodward
 */

package com.qa.logic

import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import scalafx.event.EventHandler

import scalafx.Includes._

class buttonLogic (val function: (String, String) => Unit) {
 
  def assign (b: Button) = { 
    b.onAction = handle {function}
  }
}
    
//  def assign(b: Button, s: String, v: String ,f: (String, String) => Unit): Unit = { 
//    b.onAction = handle {f(s, v)}
//  }  
//}