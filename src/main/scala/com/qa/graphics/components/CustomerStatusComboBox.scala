package com.qa.graphics.components

import scalafx.scene.control.ComboBox
import scalafx.collections.ObservableBuffer

class CustomerStatusComboBox extends ComboBox[String] {
  val testStrings = ObservableBuffer[String]("", "Placed", "Claimed", "Picked", "Packed", "Out for Delivery")
  promptText = "Choose one"
  minWidth = 150
  //Here is where the options for the comboBox are added to it
  items = testStrings
}