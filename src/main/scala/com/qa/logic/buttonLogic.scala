/**
 * @author dwoodward
 */

package com.qa.logic

import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import scalafx.event.EventHandler

import scalafx.Includes._

/**
 * Unimplemented class trying to get a way for a button to be given an execution function to run.
 * In future versions if this could be made to work a lot of lines of code could be saved.
 */
class buttonLogic (val function: (String, String) => Unit) {
 
  def assign (b: Button) = { 
    b.onAction = handle {function}
  }
}
    
//  def assign(b: Button, s: String, v: String ,f: (String, String) => Unit): Unit = { 
//    b.onAction = handle {f(s, v)}
//  }  
//}