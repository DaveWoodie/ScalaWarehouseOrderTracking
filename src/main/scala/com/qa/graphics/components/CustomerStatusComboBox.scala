package com.qa.graphics.components

import scalafx.scene.control.ComboBox
import scalafx.collections.ObservableBuffer

/**
 * Class to make the status picking box for a customer order. 
 * Full implemenetation will include a pull for the statues from the database and the selection being pushed back.
 */
class CustomerStatusComboBox extends ComboBox[String] {
  val testStrings = ObservableBuffer[String]("", "Placed", "Claimed", "Picked", "Packed", "Out for Delivery")
  promptText = "Choose one"
  minWidth = 150
  //Here is where the options for the comboBox are added to it
  items = testStrings
}