package com.qa.graphics.components

import scalafx.scene.control.ComboBox
import scalafx.collections.ObservableBuffer

class StatusComboBox extends ComboBox[String] {
  val testStrings = ObservableBuffer[String]("", "Status 1", "Status 2", "Status 3")
  promptText = "Choose one"
  minWidth = 150
  //Here is where the options for the comboBox are added to it
  items = testStrings
}