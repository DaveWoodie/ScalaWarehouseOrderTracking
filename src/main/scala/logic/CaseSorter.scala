package logic

import scalafx.scene.paint.Color
import scalafx.scene.paint.Color._
import scalafx.scene.control.Button
import scalafx.Includes._
import controllers.purchaseOrderController
import graphics.components.SinglePurchaseOrderUpdateStatusButton

class CaseSorter {
  
  /**
   * Method to return a colour dependent on the status of the Purchase Order. Match Statements!
   */
  def getColour(i: String): Color = i match {
    case "1" => Color.Red
    case "2" => Color.Orange
    case "3" => Color.LimeGreen
    case _ => Color.White
  }
  
  /**
   * Method to interpret the status into a Colour for printing.
   */
  def comboBoxInterpretFilterStatus(s: String): Int = s match {
    case "Status 1" => 1
    case "Status 2" => 2
    case "Status 3" => 3
    case _ => 0
  }
  
  /**
   * Quick case selection for the button to create
   */
  def makeUpdateButton(status: String, id: String): Button = status match {

    case "1" => {val v = new SinglePurchaseOrderUpdateStatusButton; v.placedStatusButton(status, id)}
    case "2" => {val v = new SinglePurchaseOrderUpdateStatusButton; v.deliveredStatusButton(status, id)}
    case "3" => {val v = new SinglePurchaseOrderUpdateStatusButton; v.completeStatusButton}
  }
  
}