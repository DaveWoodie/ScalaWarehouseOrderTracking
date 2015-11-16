package com.qa.graphics.components

import scalafx.scene.control.ComboBox
import scalafx.collections.ObservableBuffer

/**
 * Class for building a comboBox with only the three statuses in it. 
 * In a future version this will pull the statuses from the database.
 */
class StatusComboBox extends ComboBox[String] {
  val testStrings = ObservableBuffer[String]("", "Status 1", "Status 2", "Status 3")
  promptText = "Choose one"
  minWidth = 150
  //Here is where the options for the comboBox are added to it
  items = testStrings
}